package br.ucsal.eq02;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AtomTable {

    private List<Atom> atoms;

    public AtomTable(List<Atom> atoms) {
        this.atoms = atoms;
    }

    public List<Atom> getAtoms(String lexeme){

        List<Atom> atoms = new ArrayList<>();

        for (AtomType atomType: AtomType.getValuesSorted()) {
           for(Atom atom: getAtomsForType(atomType)) {
               if (lexeme.matches(atom.getRegex())) {
                   atoms.add(atom);
               }
           }
            // devia sumir
           if(atoms.size() > 0)
               return atoms;
        }
        return atoms;
    }

    public boolean existisAtom(String lexeme){
        return this.getAtoms(lexeme).size() > 0;
    }

    public List<Atom> getAtomsForType(AtomType atomType) {
        return this.atoms.stream().filter(x -> x.getAtomType().equals(atomType)).collect(Collectors.toList());
    }

    public void setAtoms(List<Atom> atoms) {
        this.atoms = atoms;
    }

    public void addAtom(Atom atom){
        this.atoms.add(atom);
    }

    public void getAllChars(){

    }
}
