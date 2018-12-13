package br.ucsal.eq02;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StaticChecker {

    private AtomTable atomTable;
    private File file;
    private LexicalFilter lexicalFilter;

    private List<Lexeme> lexemes;
    private List<String> stayPoints;

    private SymbolTable symbolTable;
    private LexicalAnalysisReport lexicalAnalysisReport;

    public StaticChecker(AtomTable atomTable, File file, LexicalFilter lexicalFilter, List<String> stayPoints) {
        this.atomTable = atomTable;
        this.file = file;
        this.lexicalFilter = lexicalFilter;
        this.stayPoints = stayPoints;
    }


    public StringBuffer getFileText() throws IOException {
        return new StringBuffer(new String(Files.readAllBytes(Paths.get(file.getPath()))));
    }

    public void lexicalScanner() throws IOException {

        StringBuffer text;
        this.lexemes = new ArrayList<>();
        this.lexicalFilter.setText(getFileText());

        text = lexicalFilter.applyLexicalFilters().getText();
        System.out.println(text);
        int lastLexemeIndex = 0;
        String lexeme;

        for (int i = 1;i < text.length(); i++){

            String actualLexeme = text.substring(lastLexemeIndex, i);

            if(!atomTable.existisAtom(actualLexeme)){
                if(stayPoints.stream().anyMatch(x -> actualLexeme.matches(x))){
                    continue;
                }

                if(atomTable.existisAtom(text.substring(lastLexemeIndex, (i-1)))){
                    lexeme = text.substring(lastLexemeIndex,(i-1));
                    this.lexemes.add(new Lexeme(
                            lexeme,
                            atomTable.getAtoms(lexeme),
                            lastLexemeIndex,
                            (i-1)));
                }
                lastLexemeIndex = i - 1;
            }
        }
    }

    public void lexicalAnalysis(){

        this.lexemes.forEach(System.out::println);
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public LexicalAnalysisReport getLexicalAnalysisReport() {
        return lexicalAnalysisReport;
    }
}
