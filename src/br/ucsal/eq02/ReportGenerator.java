package br.ucsal.eq02;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class ReportGenerator {

    private List<Lexeme> lexemes;
    private SymbolTable symbolTable;

    public ReportGenerator(List<Lexeme> lexemes, SymbolTable symbolTable) {
        this.lexemes = lexemes;
        this.symbolTable = symbolTable;
    }

    public void generate(String path) throws FileNotFoundException, UnsupportedEncodingException {
        this.generateReport(path);
        this.generateTable(path);
    }

    public void generateReport(String path) throws FileNotFoundException, UnsupportedEncodingException {

        PrintWriter writer = new PrintWriter(path + "report.lex", "UTF-8");
        for (Lexeme lexeme : lexemes) {

            String line = lexeme.getAtom().getCode() + "   " + lexeme.getLexeme()
                    + "   " + (lexeme.getSymbolTablePosition() != 0 ? lexeme.getSymbolTablePosition() : "-");

            writer.println(line);
        }
        writer.close();

    }


    public void generateTable(String path) throws FileNotFoundException, UnsupportedEncodingException {

        PrintWriter writer = new PrintWriter(path + "table.tab", "UTF-8");
        for (Symbol symbol : symbolTable.getSymbols()) {


            String line = symbol.getId() + "   " + symbol.getLexeme().getLexeme()
                    + "   " + symbol.getLexeme().getAtom().getName()
                    + "   " + symbol.getLexeme().getAtom().getCode()
                    + "   " + symbol.getSymbolType().getSigla()
                    + "   " + symbol.getQuantitiyBeforeTruncation()
                    + "   " + symbol.getQuantitiyAfterTruncation() + "   "
                    + Arrays.stream(symbol.getPositions()).mapToObj(x -> String.valueOf(x))
                    .reduce((x, y) -> x + " " + y).get();

            writer.println(line);
        }
        writer.close();

    }
}
