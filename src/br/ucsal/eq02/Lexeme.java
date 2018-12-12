package br.ucsal.eq02;

import java.util.List;

public class Lexeme {

    public String lexeme;
    public List<Atom> atom;
    public int startIndex;
    public int endIndex;

    public Lexeme(String lexeme, List<Atom> atom, int startIndex, int endIndex) {
        this.lexeme = lexeme;
        this.atom = atom;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "lexeme='" + lexeme + '\'' +
                ", atom=" + atom +
                ", startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                '}';
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public List<Atom> getAtom() {
        return atom;
    }

    public void setAtom(List<Atom> atom) {
        this.atom = atom;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
