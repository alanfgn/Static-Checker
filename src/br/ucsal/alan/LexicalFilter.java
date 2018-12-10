package br.ucsal.alan;

import java.util.List;

public class LexicalFilter {

    private List<String> firstLevelRegexes;
    private List<String> secondLevelRegexes;
    private String text;

    public LexicalFilter(String text, List<String>  firstLevelRegexes, List<String> secondLevelRegexes){
        this.text = text;
        this.firstLevelRegexes = firstLevelRegexes;
        this.secondLevelRegexes = secondLevelRegexes;
    }

    public LexicalFilter(String text, List<String>  firstLevelRegexes){
        this.text = text;
        this.firstLevelRegexes = firstLevelRegexes;
    }

    public LexicalFilter(String text){
        this.text = text;
    }

    public static String applyFilter(String text, List<String> regexes){
        for (String regex : regexes) {
            text = text.replaceAll(regex,"");
        }
        return text;
    }

    public LexicalFilter applyFirstLevelFilter(){
        return applyFirstLevelFilter(this.text);
    }


    public LexicalFilter applyFirstLevelFilter(String text){
        this.text = applyFilter(text, this.firstLevelRegexes);
        return this;
    }


    public LexicalFilter applySecondLevelFilter(){
        throw new UnsupportedOperationException();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
