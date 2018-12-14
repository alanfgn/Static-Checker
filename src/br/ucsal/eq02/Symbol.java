package br.ucsal.eq02;

import java.util.Arrays;
import java.util.List;

public class Symbol {

    private static int count = 1;

    private int id;

    private Lexeme lexeme;
    private SymbolType symbolType;

    private int quantitiyBeforeTruncation;
    private int quantitiyAfterTruncation;

    private int[] positions;

    public Symbol(Lexeme lexeme, SymbolType symbolType, int[] positions) {

        this.id = Symbol.count;
        Symbol.count++;

        this.lexeme = lexeme;
        this.symbolType = symbolType;
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "id=" + id +
                ", lexeme=" + lexeme +
                ", symbolType=" + symbolType +
                ", quantitiyBeforeTruncation=" + quantitiyBeforeTruncation +
                ", quantitiyAfterTruncation=" + quantitiyAfterTruncation +
                ", positions=" + Arrays.toString(positions) +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lexeme getLexeme() {
        return lexeme;
    }

    public void setLexeme(Lexeme lexeme) {
        this.lexeme = lexeme;
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }

    public void setSymbolType(SymbolType symbolType) {
        this.symbolType = symbolType;
    }

    public int getQuantitiyBeforeTruncation() {
        return quantitiyBeforeTruncation;
    }

    public void setQuantitiyBeforeTruncation(int quantitiyBeforeTruncation) {
        this.quantitiyBeforeTruncation = quantitiyBeforeTruncation;
    }

    public int getQuantitiyAfterTruncation() {
        return quantitiyAfterTruncation;
    }

    public void setQuantitiyAfterTruncation(int quantitiyAfterTruncation) {
        this.quantitiyAfterTruncation = quantitiyAfterTruncation;
    }

    public int[] getPositions() {
        return positions;
    }

    public void setPositions(int[] positions) {
        this.positions = positions;
    }
}
