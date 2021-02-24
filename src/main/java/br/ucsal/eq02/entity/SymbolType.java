package br.ucsal.eq02.entity;

import java.util.List;

public class SymbolType {

    private String name;
    private String acronym;
    private List<SymbolRule> symbolsRules;

    public SymbolType(String name, String acronym, List<SymbolRule> symbolsRules) {
        this.name = name;
        this.acronym = acronym;
        this.symbolsRules = symbolsRules;
    }

    public boolean isThisSymbol(int position, List<Lexeme> text) {
        return symbolsRules.stream().anyMatch(x -> x.isThisSymbol(position, text));
    }

    @Override
    public String toString() {
        return "SymbolType{" +
                "name='" + name + '\'' +
                ", acronym='" + acronym + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public List<SymbolRule> getSymbolsRules() {
        return symbolsRules;
    }

    public void setSymbolsRules(List<SymbolRule> symbolsRules) {
        this.symbolsRules = symbolsRules;
    }
}
