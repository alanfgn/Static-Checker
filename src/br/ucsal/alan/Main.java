package br.ucsal.alan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static void main(String [] args){
        System.out.println("Static Checker 2018.2");

        try {
            FileReader fileReader = new FileReader(
                    new File("/home/alan/Projetos/eclipse-workspace/StaticChecker/src/br/ucsal/compiladores/example.182"));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //Scanner scanner = new Scanner(bufferedReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("::::");
                System.out.println(line);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
