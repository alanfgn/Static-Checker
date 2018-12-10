package br.ucsal.alan;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable {

    private List<Symbol> symbolLines = new ArrayList<>();

    public List<Symbol> getSymbolLines() {
        return symbolLines;
    }

    public void setSymbolLines(List<Symbol> symbolLines) {
        this.symbolLines = symbolLines;
    }
}
