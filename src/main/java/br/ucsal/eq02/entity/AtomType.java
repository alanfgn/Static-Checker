package br.ucsal.eq02.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum AtomType {
    SYMBOL(1), WORD(2), IDENTIFIER(3), SUB_MACHINE(4);

    private int importance;

    AtomType(int importance) {
        this.importance = importance;
    }

    public static List<AtomType> getValuesSorted() {
        List<AtomType> atomTypes = Arrays.stream(AtomType.values()).collect(Collectors.toList());
        Collections.sort(atomTypes, new Comparator<AtomType>() {
            @Override
            public int compare(AtomType atomType, AtomType atomType1) {
                return Integer.compare(atomType.getImportance(), atomType1.getImportance());
            }
        });
        return atomTypes;
    }

    public int getImportance() {
        return importance;
    }
}
