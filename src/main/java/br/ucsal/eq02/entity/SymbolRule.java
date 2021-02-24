package br.ucsal.eq02.entity;

import br.ucsal.eq02.entity.Atom;
import br.ucsal.eq02.entity.Lexeme;

import java.util.List;

public class SymbolRule {

    public List<Atom> beforeAtoms;
    public Atom atom;
    public List<Atom> afterAtoms;

    public SymbolRule(List<Atom> beforeAtoms, Atom atom, List<Atom> afterAtoms) {
        this.beforeAtoms = beforeAtoms;
        this.atom = atom;
        this.afterAtoms = afterAtoms;
    }

    public SymbolRule(Atom atom) {
        this(null, atom, null);
    }

    public boolean isThisSymbol(int position, List<Lexeme> text){
        Lexeme lexeme = text.get(position);
        boolean before = true;
        boolean here = true;
        boolean after = true;

        if(beforeAtoms != null){
            if(position < beforeAtoms.size() - 1){
                before = false;
            }else{
                for(int  i = 0; i < beforeAtoms.size(); i++){
                    Lexeme aux = text.get(position - (i + 1));
                    Atom aux1 = beforeAtoms.get(i);
                    before = before & text.get(position - (i + 1)).getAtom().equals(beforeAtoms.get(i));
                }
            }
        }

        if(atom != null){
            here = text.get(position).getAtom().equals(this.atom);
        }

        if(afterAtoms != null){
            if(position - (text.size() -1) < afterAtoms.size()) {
                after = false;
            }else{
                for (int  i = 0; i < afterAtoms.size(); i++) {
                    after = after & text.get(position + (i +1)).getAtom().equals(afterAtoms.get(i));
                }
            }
        }

        return before & here & after;
    }

}
