package br.ucsal.alan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class StaticChecker {

    private File file;
    private LexicalFilter lexicalFilter;
    private SymbolTable symbolTable;

    private List<Atom> atoms;

    public StaticChecker(File file, LexicalFilter lexicalFilter){
        this.file = file;
        this.lexicalFilter = lexicalFilter;
    }

    public StaticChecker(String path, LexicalFilter lexicalFilter){
        this(new File(path), lexicalFilter);
    }

    public void analyze() throws FileNotFoundException {

        Scanner scanner = new Scanner(new BufferedReader(new FileReader(this.file)));

        while (scanner.hasNext()){
            String line = scanner.nextLine();
            line = lexicalFilter.applyFirstLevelFilter(line).getText();

        }

    }

    public void generateSymbolTable(){

    }

    public void generateLexicalAnalysisReport(){

    }

}
