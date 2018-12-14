package br.ucsal.eq02;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("StaticChecker 2018.2 eq02");

        HashMap<String, String> firstLevelFilter = new HashMap<>();

        List<String> stayPoints = new ArrayList<>();

        firstLevelFilter.put("//.*", "");
        firstLevelFilter.put("(?s)/\\*.*?\\*/", "");
        firstLevelFilter.put("[\\t\\r\\n]+", " ");
        firstLevelFilter.put("[\\s](?=(?:\"[^\"]*\"|[^\"])*$)\n", " ");
        firstLevelFilter.put("(?![a-z0-9\\'\"\\$\\+\\- \\.\\!=#&\\(/;\\[\\{<>%\\)\\*,\\]\\|\\}_]).", "");

        LexicalFilter lexicalFilter = new LexicalFilter(firstLevelFilter);

        List<Atom> wordAtoms = new ArrayList<>();

        wordAtoms.add(new Atom("A01", "bool", AtomType.WORD));
        wordAtoms.add(new Atom("A02", "while", AtomType.WORD));
        wordAtoms.add(new Atom("A03", "break", AtomType.WORD));
        wordAtoms.add(new Atom("A04", "void", AtomType.WORD));
        wordAtoms.add(new Atom("A05", "char", AtomType.WORD));
        wordAtoms.add(new Atom("A06", "true", AtomType.WORD));
        wordAtoms.add(new Atom("A07", "else", AtomType.WORD));
        wordAtoms.add(new Atom("A08", "string", AtomType.WORD));
        wordAtoms.add(new Atom("A09", "end", AtomType.WORD));
        wordAtoms.add(new Atom("A10", "return", AtomType.WORD));
        wordAtoms.add(new Atom("A11", "false", AtomType.WORD));
        wordAtoms.add(new Atom("A12", "program", AtomType.WORD));
        wordAtoms.add(new Atom("A13", "float", AtomType.WORD));
        wordAtoms.add(new Atom("A14", "int", AtomType.WORD));
        wordAtoms.add(new Atom("A15", "if", AtomType.WORD));
        wordAtoms.add(new Atom("A16", "begin", AtomType.WORD));

        AtomTable atomTable = new AtomTable(wordAtoms);
        List<Atom> symbolAtoms = new ArrayList<>();

        symbolAtoms.add(new Atom("B01", "!=", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B01", "#", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B02", "&", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B03", "(", "\\(", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B04", "/", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B05", ";", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B06", "[", "\\[", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B07", "{", "\\{", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B08", "+", "\\+", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B09", "<=", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B10", "=", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B11", ">=", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B12", "!", "\\!", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B13", "%", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B14", ")", "\\)", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B15", "*", "\\*", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B16", ",", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B17", "]", "\\]", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B18", "|", "\\|", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B19", "}", "\\}", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B20", "<", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B21", "==", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B22", ">", AtomType.SYMBOL));
        symbolAtoms.add(new Atom("B23", "-", "\\-", AtomType.SYMBOL));

        atomTable.appendListAtoms(symbolAtoms);
        List<Atom> identifierAtoms = new ArrayList<>();

        identifierAtoms.add(new Atom("C06", "Number", "[0-9]+", AtomType.IDENTIFIER));
        identifierAtoms.add(new Atom("C01", "Character", "\\'[a-z]\\'", AtomType.IDENTIFIER));

        stayPoints.add("\\'[a-z]?\\'?");

        identifierAtoms.add(new Atom("C02", "Constant-String", "\"[a-z 0-9\\$_\\. ]*\"", AtomType.IDENTIFIER));
        stayPoints.add("\"[a-z 0-9\\$_\\. ]*\"?");

        identifierAtoms.add(new Atom("C03", "Float-Number", "[0-9]+\\.[0-9]+(e[\\-|\\+]?[0-9]+)?", AtomType.IDENTIFIER));
        stayPoints.add("[0-9]+.");
        stayPoints.add("[0-9]+\\.[0-9]+e[\\-|\\+]?");

        String[] codesAfterAtomsFunction = {"B03"};
        List<Atom> afterAtomsFunction = atomTable.getAtomsByCodes(codesAfterAtomsFunction);

        identifierAtoms.add(new Atom("C04", "Function", "[a-z0-9_]+[a-z0-9]|[a-z]",
                AtomType.IDENTIFIER, afterAtomsFunction));

        String[] codesAfterAtomsIdentifier = {"B01","B02","B04","B05","B08","B09"
                ,"B10","B11","B12","B13","B15","B16","B17","B18","B19","B20",
                "B21","B22","B23"};
        List<Atom> afterAtomsIdentifier = atomTable.getAtomsByCodes(codesAfterAtomsIdentifier);

        identifierAtoms.add(new Atom("C05", "Identifier", "[a-z0-9_]*[a-z_]+",
                AtomType.IDENTIFIER, afterAtomsIdentifier));

        atomTable.appendListAtoms(identifierAtoms);

 //       atomTable.getAtoms().stream().forEach(System.out::println);

        File fIle = new File("/home/alan/Projetos/eclipse-workspace/StaticChecker/src/br/ucsal/compiladores/example.182");


        StaticChecker sc = new StaticChecker(atomTable, fIle, lexicalFilter, stayPoints);

        try {
            sc.lexicalScanner();
            sc.lexicalAnalysis();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
