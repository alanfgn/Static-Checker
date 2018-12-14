package br.ucsal.eq02;

import java.util.List;

public class SymbolType {

    private String name;
    private String sigla;
    private List<SymbolRule> symbolsRules;

    public SymbolType(String name, String sigla, List<SymbolRule> symbolsRules) {
        this.name = name;
        this.sigla = sigla;
        this.symbolsRules = symbolsRules;
    }

    public boolean isThisSymbol(int position, List<Lexeme> text){
        return symbolsRules.stream().anyMatch(x -> x.isThisSymbol(position, text));
    }

    @Override
    public String toString() {
        return "SymbolType{" +
                "name='" + name + '\'' +
                ", sigla='" + sigla + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<SymbolRule> getSymbolsRules() {
        return symbolsRules;
    }

    public void setSymbolsRules(List<SymbolRule> symbolsRules) {
        this.symbolsRules = symbolsRules;
    }
}
