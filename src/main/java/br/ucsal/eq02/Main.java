package br.ucsal.eq02;

import br.ucsal.eq02.entity.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("StaticChecker 2018.2 eq02");
        System.out.println("Analysing file: "+args[0]);

        if (!(args.length > 0)) {
            return;
        }

        File fIle = new File(args[0]);

        HashMap<String, String> firstLevelFilter = new HashMap<>();
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
        identifierAtoms.add(new Atom("C02", "Constant-String", "\"[a-z 0-9\\$_\\. ]*\"",
                AtomType.IDENTIFIER));
        identifierAtoms.add(new Atom("C03", "Float-Number", "[0-9]+\\.[0-9]+(e[\\-|\\+]?[0-9]+)?",
                AtomType.IDENTIFIER));

        List<String> stayPoints = new ArrayList<>();
        stayPoints.add("\\'[a-z]?\\'?");
        stayPoints.add("\"[a-z 0-9\\$_\\. ]*\"?");
        stayPoints.add("[0-9]+.");
        stayPoints.add("[0-9]+\\.[0-9]+e[\\-|\\+]?");

        List<Atom> afterAtomsFunction = atomTable.getAtomsByCodes(new String[]{"B03"});

        identifierAtoms.add(new Atom("C04", "Function", "[a-z0-9_]+[a-z0-9]|[a-z]",
                AtomType.IDENTIFIER, afterAtomsFunction));

        List<Atom> afterAtomsIdentifier =
                atomTable.getAtomsByCodes(new String[]{"B01", "B02", "B04", "B05", "B08", "B09"
                        , "B10", "B11", "B12", "B13", "B15", "B16", "B17", "B18", "B19", "B20",
                        "B21", "B22", "B23"});

        identifierAtoms.add(new Atom("C05", "Identifier", "[a-z0-9_]*[a-z_]+",
                AtomType.IDENTIFIER, afterAtomsIdentifier));

        atomTable.appendListAtoms(identifierAtoms);


        List<SymbolRule> symbolRulesFL = new ArrayList<>();
        symbolRulesFL.add(new SymbolRule(atomTable.getAtomByCode("C03")));
        symbolRulesFL.add(new SymbolRule(
                atomTable.getAtomsByCodes(new String[]{"A13"}),
                atomTable.getAtomByCode("C04"),
                null
        ));

        symbolRulesFL.add(new SymbolRule(
                atomTable.getAtomsByCodes(new String[]{"A13"}),
                atomTable.getAtomByCode("C05"),
                null
        ));
        SymbolType symbolTypeFL = new SymbolType("Ponto Flutuante", "FL", symbolRulesFL);


        List<SymbolRule> symbolRulesIN = new ArrayList<>();
        symbolRulesIN.add(new SymbolRule(atomTable.getAtomByCode("C06")));
        symbolRulesIN.add(new SymbolRule(
                atomTable.getAtomsByCodes(new String[]{"A14"}),
                atomTable.getAtomByCode("C04"),
                null
        ));
        symbolRulesIN.add(new SymbolRule(
                atomTable.getAtomsByCodes(new String[]{"A14"}),
                atomTable.getAtomByCode("C05"),
                null
        ));
        SymbolType symbolTypeIN = new SymbolType("Inteiro", "IN", symbolRulesIN);

        List<SymbolRule> symbolRulesST = new ArrayList<>();
        symbolRulesST.add(new SymbolRule(atomTable.getAtomByCode("C02")));
        symbolRulesST.add(new SymbolRule(
                atomTable.getAtomsByCodes(new String[]{"A08"}),
                atomTable.getAtomByCode("C04"),
                null
        ));
        symbolRulesST.add(new SymbolRule(
                atomTable.getAtomsByCodes(new String[]{"A08"}),
                atomTable.getAtomByCode("C05"),
                null
        ));
        SymbolType symbolTypeST = new SymbolType("String", "ST", symbolRulesST);

        List<SymbolRule> symbolRulesCH = new ArrayList<>();
        symbolRulesCH.add(new SymbolRule(atomTable.getAtomByCode("C01")));
        symbolRulesCH.add(new SymbolRule(
                atomTable.getAtomsByCodes(new String[]{"A05"}),
                atomTable.getAtomByCode("C04"),
                null
        ));
        symbolRulesCH.add(new SymbolRule(
                atomTable.getAtomsByCodes(new String[]{"A05"}),
                atomTable.getAtomByCode("C05"),
                null
        ));
        SymbolType symbolTypeCH = new SymbolType("Character", "CH", symbolRulesCH);

        List<SymbolRule> symbolRulesBO = new ArrayList<>();
        symbolRulesBO.add(new SymbolRule(
                atomTable.getAtomsByCodes(new String[]{"A01"}),
                atomTable.getAtomByCode("C04"),
                null
        ));
        symbolRulesBO.add(new SymbolRule(
                atomTable.getAtomsByCodes(new String[]{"A01"}),
                atomTable.getAtomByCode("C05"),
                null
        ));

        SymbolType symbolTypeBO = new SymbolType("Booleano", "BO", symbolRulesBO);

        List<SymbolRule> symbolRulesVO = new ArrayList<>();
        symbolRulesVO.add(new SymbolRule(
                atomTable.getAtomsByCodes(new String[]{"A04"}),
                atomTable.getAtomByCode("C04"),
                null
        ));
        SymbolType symbolTypeVO = new SymbolType("Void", "VO", symbolRulesVO);

        List<SymbolType> symbolTypes = new ArrayList<>();
        symbolTypes.add(symbolTypeFL);
        symbolTypes.add(symbolTypeIN);
        symbolTypes.add(symbolTypeST);
        symbolTypes.add(symbolTypeCH);
        symbolTypes.add(symbolTypeBO);
        symbolTypes.add(symbolTypeVO);
        SymbolTable symbolTable = new SymbolTable(symbolTypes);

        StaticChecker sc = new StaticChecker(atomTable, fIle, lexicalFilter, stayPoints, symbolTable, 35);

        try {
            sc.lexicalScanner();
            sc.lexicalAnalysis();
            ReportGenerator reportGenerator = new ReportGenerator(sc.getLexemes(), sc.getSymbolTable());
            reportGenerator.generate("./");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
