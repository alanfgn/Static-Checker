package br.ucsal.eq02;

import br.ucsal.eq02.entity.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StaticChecker {

    private AtomTable atomTable;
    private File file;
    private LexicalFilter lexicalFilter;

    private List<Lexeme> lexemes;
    private List<String> stayPoints;

    private SymbolTable symbolTable;
    private int limit;

    public StaticChecker(AtomTable atomTable, File file, LexicalFilter lexicalFilter, List<String> stayPoints,
                         SymbolTable symbolTable, int limit) {
        this.atomTable = atomTable;
        this.file = file;
        this.lexicalFilter = lexicalFilter;
        this.stayPoints = stayPoints;
        this.symbolTable = symbolTable;
        this.limit = limit;
    }

    public StringBuffer getFileText() throws IOException {
        return new StringBuffer(new String(Files.readAllBytes(Paths.get(file.getPath()))));
    }

    public void lexicalScanner() throws IOException {

        StringBuffer text;
        this.lexemes = new ArrayList<>();
        this.lexicalFilter.setText(getFileText());
        this.lexicalFilter.toLowerCase();

        text = lexicalFilter.applyLexicalFilters().getText();
        int lastLexemeIndex = 0;
        String lexeme;

        for (int i = 1; i < text.length(); i++) {

            String actualLexeme = text.substring(lastLexemeIndex, i);

            if (!atomTable.haveAtom(actualLexeme)) {
                if (stayPoints.stream().anyMatch(x -> actualLexeme.matches(x))) {
                    continue;
                }

                if (atomTable.haveAtom(text.substring(lastLexemeIndex, (i - 1)))) {
                    lexeme = text.substring(lastLexemeIndex, (i - 1));

                    this.lexemes.add(new Lexeme(
                            lexeme,
                            atomTable.getAtoms(lexeme),
                            lastLexemeIndex,
                            (i - 1)));
                }

                lastLexemeIndex = i - 1;
            }
        }
    }

    public void lexicalAnalysis() throws IOException {
        this.resolveAmbiguity();
        this.populeSymbolTable();
        this.truncate();
    }

    public void truncate() {
        for (Symbol symbol : symbolTable.getSymbols()) {
            if (symbol.getLexeme().getLexeme().length() > 35) {
                symbol.setQuantitiyAfterTruncation(symbol.getLexeme().getLexeme().length());
                symbol.setQuantitiyAfterTruncation(35);
                symbol.getLexeme().setLexeme(symbol.getLexeme().getLexeme().substring(0, 35));
            }
        }
    }

    public void populeSymbolTable() throws IOException {
        for (int i = 0; i < this.lexemes.size(); i++) {
            if (this.lexemes.get(i).getAtom().getAtomType().equals(AtomType.IDENTIFIER)) {
                Symbol symbol = symbolTable.getSymbol(this.lexemes.get(i));

                if (symbol == null) {
                    symbol = new Symbol(
                            lexemes.get(i),
                            symbolTable.getSymbolType(i, this.lexemes),
                            findPositions(lexemes.get(i)));
                }

                this.symbolTable.addSymbol(symbol);
                this.lexemes.get(i).setSymbolTablePosition(symbol.getId());
            }
        }
    }

    public void resolveAmbiguity() {

        List<AtomType> atomsSorted = AtomType.getValuesSorted();

        for (int i = 0; i < this.lexemes.size(); i++) {
            Lexeme lexeme = this.lexemes.get(i);

            if (lexeme.isAmbiguous()) {
                for (AtomType atomType : atomsSorted) {
                    if (lexeme.getAtoms().stream().map(x -> x.getAtomType()).anyMatch(x -> x.equals(atomType))) {
                        lexeme.setAtoms(
                                lexeme.getAtoms().stream()
                                        .filter(x -> x.getAtomType().equals(atomType))
                                        .collect(Collectors.toList()));
                    }
                }
            }


            if (lexeme.isAmbiguous()) {

                if (i < this.lexemes.size()) {
                    Lexeme afterLexeme = this.lexemes.get(i + 1);
                    lexeme.setAtoms(
                            lexeme.getAtoms().stream().filter(x -> afterLexeme.getAtoms()
                                    .stream().anyMatch(y -> x.isPossibleAfterAtom(y)))
                                    .collect(Collectors.toList()));
                }

                if (i >= 0) {
                    Lexeme beforeLexeme = this.lexemes.get(i - 1);
                    lexeme.setAtoms(
                            lexeme.getAtoms().stream().filter(x -> beforeLexeme.getAtoms()
                                    .stream().anyMatch(y -> x.isPossibleBeforeAtom(y)))
                                    .collect(Collectors.toList()));
                }

            }
        }
    }

    public int[] findPositions(Lexeme lexeme) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(this.file));

        int[] positions = new int[5];
        int countPositions = 0;

        String st;
        int count = 1;
        while ((st = br.readLine()) != null) {

            if (st.matches(".*" + lexeme.getLexeme() + ".*")) {
                positions[countPositions] = count;
                countPositions++;
            }

            if (countPositions == 5) {
                break;
            }
            count++;
        }

        br.close();
        return positions;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public List<Lexeme> getLexemes() {
        return lexemes;
    }
}
