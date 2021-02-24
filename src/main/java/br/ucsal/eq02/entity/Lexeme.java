package br.ucsal.eq02.entity;

import br.ucsal.eq02.entity.Atom;

import java.util.List;

public class Lexeme {

    public String lexeme;
    public List<Atom> atoms;
    public int startIndex;
    public int endIndex;
    public int symbolTablePosition;

    public Lexeme(String lexeme, List<Atom> atoms, int startIndex, int endIndex) {
        this.lexeme = lexeme;
        this.atoms = atoms;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "lexeme='" + lexeme + '\'' +
                ", atom=" + atoms +
                ", startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                '}';
    }

    public boolean isAmbiguous() {
        return this.getAtoms().size() > 1;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public List<Atom> getAtoms() {
        return atoms;
    }

    public void setAtoms(List<Atom> atoms) {
        this.atoms = atoms;
    }

    public Atom getAtom() {
        if (!this.isAmbiguous())
            return this.atoms.get(0);
        else
            return null;
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

    public int getSymbolTablePosition() {
        return symbolTablePosition;
    }

    public void setSymbolTablePosition(int symbolTablePosition) {
        this.symbolTablePosition = symbolTablePosition;
    }
}
