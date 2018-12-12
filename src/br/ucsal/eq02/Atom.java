package br.ucsal.eq02;

public class Atom {

    private String code;
    private String name;
    private String regex;
    private AtomType atomType;

    public Atom(String code,String name,  String regex, AtomType atomType) {
        this.code = code;
        this.name = name;
        this.regex = regex;
        this.atomType = atomType;
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
}
