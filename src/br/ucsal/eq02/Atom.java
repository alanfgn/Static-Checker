package br.ucsal.eq02;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Atom {

    private String code;
    private String name;
    private String regex;
    private AtomType atomType;

    private List<Atom> possibleAfterAtoms;
    private List<Atom> possibleBeforeAtoms;


    public Atom(String code, String name, String regex, AtomType atomType, List<Atom> possibleAfterAtoms, List<Atom> possibleBeforeAtoms) {
        this.code = code;
        this.name = name;
        this.regex = regex;
        this.atomType = atomType;
        this.possibleAfterAtoms = possibleAfterAtoms;
        this.possibleBeforeAtoms = possibleBeforeAtoms;
    }

    public Atom(String code, String name, String regex, AtomType atomType, List<Atom> possibleAfterAtoms) {
        this(code, name, regex, atomType, possibleAfterAtoms, new ArrayList<>());
    }

    public Atom(String code, String name, String regex, AtomType atomType) {
        this(code, name, regex, atomType, new ArrayList<>(), new ArrayList<>());
    }

    public Atom(String code, String name, AtomType atomType) {
        this(code, name, name, atomType);
    }

    @Override
    public String toString() {
        return "Atom{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", atomType=" + atomType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atom atom = (Atom) o;
        return Objects.equals(code, atom.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    public boolean isPossibleAfterAtom(Atom atom){
        if(this.getPossibleAfterAtoms().size() == 0) return true;
        return this.getPossibleAfterAtoms().contains(atom);
    }

    public boolean isPossibleBeforeAtom(Atom atom){
        if(this.getPossibleBeforeAtoms().size() == 0) return true;
        return this.getPossibleBeforeAtoms().contains(atom);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public AtomType getAtomType() {
        return atomType;
    }

    public void setAtomType(AtomType atomType) {
        this.atomType = atomType;
    }

    public List<Atom> getPossibleAfterAtoms() {
        return possibleAfterAtoms;
    }

    public void setPossibleAfterAtoms(List<Atom> possibleAfterAtoms) {
        this.possibleAfterAtoms = possibleAfterAtoms;
    }

    public List<Atom> getPossibleBeforeAtoms() {
        return possibleBeforeAtoms;
    }

    public void setPossibleBeforeAtoms(List<Atom> possibleBeforeAtoms) {
        this.possibleBeforeAtoms = possibleBeforeAtoms;
    }
}
