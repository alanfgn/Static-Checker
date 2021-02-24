package br.ucsal.eq02.entity;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable {

    private List<SymbolType> symbolTypes = new ArrayList<>();
    private List<Symbol> symbols = new ArrayList<>();

    public SymbolTable(List<SymbolType> symbolTypes) {
        this.symbolTypes = symbolTypes;
    }

    public SymbolType getSymbolType(int position, List<Lexeme> text) {
        return this.symbolTypes.stream()
                .filter(x -> x.isThisSymbol(position, text)).findFirst().orElse(null);
    }

    public Symbol getSymbol(Lexeme lexeme) {
        return symbols.stream().filter(x -> x.getLexeme().lexeme.equals(lexeme.getLexeme())).findFirst().orElse(null);
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }

    public void addSymbol(Symbol symbol) {
        this.symbols.add(symbol);
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }


}
