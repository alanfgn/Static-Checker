package br.ucsal.eq02;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String [] args){

        System.out.println("::::::::::::::::::::::::::::::");

        HashMap<String, String> firstLevelFilter = new HashMap<>();

        List<String> naturalDelimiters = new ArrayList<>();
        //naturalDelimiters.add("\\s");

        firstLevelFilter.put("//.*","");
        firstLevelFilter.put("(?s)/\\*.*?\\*/", "");
        firstLevelFilter.put("[\\t\\r\\n]+"," ");
        firstLevelFilter.put("[\\s](?=(?:\"[^\"]*\"|[^\"])*$)\n", " ");
        firstLevelFilter.put("(?![a-z0-9\\'\"\\$\\+\\- \\.\\!=#&\\(/;\\[\\{<>%\\)\\*,\\]\\|\\}_]).", "");

        LexicalFilter lexicalFilter = new LexicalFilter(firstLevelFilter);

        List<Atom> atoms = new ArrayList<>();

        atoms.add(new Atom("A01","bool", AtomType.WORD));
        atoms.add(new Atom("A02","while", AtomType.WORD));
        atoms.add(new Atom("A03","break", AtomType.WORD));
        atoms.add(new Atom("A04","void", AtomType.WORD));
        atoms.add(new Atom("A05","char", AtomType.WORD));
        atoms.add(new Atom("A06","true", AtomType.WORD));
        atoms.add(new Atom("A07","else", AtomType.WORD));
        atoms.add(new Atom("A08","string", AtomType.WORD));
        atoms.add(new Atom("A09","end", AtomType.WORD));
        atoms.add(new Atom("A10","return", AtomType.WORD));
        atoms.add(new Atom("A11","false", AtomType.WORD));
        atoms.add(new Atom("A12","program", AtomType.WORD));
        atoms.add(new Atom("A13","float", AtomType.WORD));
        atoms.add(new Atom("A14","int", AtomType.WORD));
        atoms.add(new Atom("A15","if", AtomType.WORD));
        atoms.add(new Atom("A16","begin", AtomType.WORD));

        atoms.add(new Atom("B01","!=", AtomType.SYMBOL));
        atoms.add(new Atom("B01","#", AtomType.SYMBOL));
        atoms.add(new Atom("B02","&", AtomType.SYMBOL));
        atoms.add(new Atom("B03","(", "\\(", AtomType.SYMBOL));
        atoms.add(new Atom("B04","/", AtomType.SYMBOL));
        atoms.add(new Atom("B05",";", AtomType.SYMBOL));
        atoms.add(new Atom("B06","[", "\\[", AtomType.SYMBOL));
        atoms.add(new Atom("B07","{", "\\{", AtomType.SYMBOL));
        atoms.add(new Atom("B08","+", "\\+", AtomType.SYMBOL));
        atoms.add(new Atom("B09","<=", AtomType.SYMBOL));
        atoms.add(new Atom("B10","=", AtomType.SYMBOL));
        atoms.add(new Atom("B11",">=", AtomType.SYMBOL));
        atoms.add(new Atom("B12","!", "\\!",AtomType.SYMBOL));
        atoms.add(new Atom("B13","%", AtomType.SYMBOL));
        atoms.add(new Atom("B14",")", "\\)",AtomType.SYMBOL));
        atoms.add(new Atom("B15","*", "\\*",AtomType.SYMBOL));
        atoms.add(new Atom("B16",",", AtomType.SYMBOL));
        atoms.add(new Atom("B17","]", "\\]",AtomType.SYMBOL));
        atoms.add(new Atom("B18","|", "\\|", AtomType.SYMBOL));
        atoms.add(new Atom("B19","}", "\\}",AtomType.SYMBOL));
        atoms.add(new Atom("B20","<", AtomType.SYMBOL));
        atoms.add(new Atom("B21","==", AtomType.SYMBOL));
        atoms.add(new Atom("B22",">", AtomType.SYMBOL));
        atoms.add(new Atom("B23","-", "\\-",AtomType.SYMBOL));

        atoms.add(new Atom("C01","Character",       "\\'[a-z]\\'", AtomType.IDENTIFIER));
        naturalDelimiters.add("\\'[a-z]?\\'?");

        atoms.add(new Atom("C02","Constant-String", "\"[a-z 0-9\\$_\\. ]*\"", AtomType.IDENTIFIER));
        naturalDelimiters.add("\"[a-z 0-9\\$_\\. ]*\"?");

        atoms.add(new Atom("C03","Float-Number",    "[0-9]+\\.[0-9]+(e[\\-|\\+]?[0-9]+)?",AtomType.IDENTIFIER));
        naturalDelimiters.add("[0-9]+.");
        naturalDelimiters.add("[0-9]+\\.[0-9]+e[\\-|\\+]?");

        atoms.add(new Atom("C04","Function",        "[a-z0-9_]+[a-z0-9]|[a-z]", AtomType.IDENTIFIER));
        atoms.add(new Atom("C05","Identifier",      "[a-z0-9_]*[a-z_]+",AtomType.IDENTIFIER));
        atoms.add(new Atom("C06","Number",          "[0-9]+",AtomType.IDENTIFIER));

        AtomTable atomTable = new AtomTable(atoms);

        File fIle = new File("/home/alan/Projetos/eclipse-workspace/StaticChecker/src/br/ucsal/compiladores/example.182");



        System.out.println();

        StaticChecker sc = new StaticChecker(atomTable, fIle, lexicalFilter, naturalDelimiters);

        try{
            sc.lexicalScanner();
            sc.lexicalAnalysis();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
