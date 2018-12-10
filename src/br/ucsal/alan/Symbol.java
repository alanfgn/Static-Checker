package br.ucsal.alan;

public class Symbol {

    private Integer entryNumber;
    private Atom atom;
    private String lexeme;
    private String symbolType;

    private Integer afterTruncation;
    private Integer beforeTruncation;

    private Integer[] appearences;

    public Integer getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(Integer entryNumber) {
        this.entryNumber = entryNumber;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public Atom getAtom() {
        return atom;
    }

    public void setAtom(Atom atom) {
        this.atom = atom;
    }

    public String getSymbolType() {
        return symbolType;
    }

    public void setSymbolType(String symbolType) {
        this.symbolType = symbolType;
    }

    public Integer getAfterTruncation() {
        return afterTruncation;
    }

    public void setAfterTruncation(Integer afterTruncation) {
        this.afterTruncation = afterTruncation;
    }

    public Integer getBeforeTruncation() {
        return beforeTruncation;
    }

    public void setBeforeTruncation(Integer beforeTruncation) {
        this.beforeTruncation = beforeTruncation;
    }

    public Integer[] getAppearences() {
        return appearences;
    }

    public void setAppearences(Integer[] appearences) {
        this.appearences = appearences;
    }
}

