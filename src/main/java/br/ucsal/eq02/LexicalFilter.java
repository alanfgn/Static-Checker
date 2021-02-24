package br.ucsal.eq02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalFilter {

    private HashMap<String, String> firstLevelFilters;
    private List<String> secondLevelFilters;
    private StringBuffer text;

    public LexicalFilter(HashMap<String, String> firstLevelFilters, List<String> secondLevelFilters, StringBuffer text) {
        this.firstLevelFilters = firstLevelFilters;
        this.secondLevelFilters = secondLevelFilters;
        this.text = text;
    }

    public LexicalFilter(HashMap<String, String> firstLevelFilters, List<String> secondLevelFilters) {
        this(firstLevelFilters, secondLevelFilters, new StringBuffer());
    }

    public LexicalFilter(HashMap<String, String> firstLevelRegexes) {
        this(firstLevelRegexes, new ArrayList<>());
    }

    public static StringBuffer applyFilter(StringBuffer text, HashMap<String, String> firstLevelFilters) {
        for (Map.Entry<String, String> filtro : firstLevelFilters.entrySet()) {
            String aux = Pattern.compile(filtro.getKey(), Pattern.MULTILINE).matcher(text).replaceAll(filtro.getValue());
            text = new StringBuffer(aux);
        }
        return text;
    }

    public LexicalFilter applyFirstLevelFilter(StringBuffer text) {
        this.text = applyFilter(text, this.firstLevelFilters);
        return this;
    }

    public LexicalFilter applyFirstLevelFilter() {
        return applyFirstLevelFilter(this.text);
    }

    public LexicalFilter applySecondLevelFilter(StringBuffer text) {
        for (String regex : this.secondLevelFilters) {
            Matcher matcher = Pattern.compile(regex, Pattern.MULTILINE).matcher(text);

            while (matcher.find()) {
                System.out.println("Lexical error 182: " + matcher.group(0));
            }
        }
        return this;
    }

    public LexicalFilter applySecondLevelFilter() {
        return applySecondLevelFilter(this.text);
    }

    public LexicalFilter applyLexicalFilters() {
        return this.applyFirstLevelFilter().applySecondLevelFilter();
    }

    public void toLowerCase(){
        for (int idx = 0; idx < this.text.length(); idx++) {
            char c = this.text.charAt(idx);
            if (c >= 65 && c <= 65 + 27) {
                this.text.setCharAt(idx, (char) ((int) (this.text.charAt(idx)) | 32));
            }
        }
    }

    public StringBuffer getText() {
        return text;
    }

    public void setText(StringBuffer text) {
        this.text = text;
    }

}
