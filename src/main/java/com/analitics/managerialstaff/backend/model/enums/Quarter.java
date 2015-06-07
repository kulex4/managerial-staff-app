package com.analitics.managerialstaff.backend.model.enums;

/**
 * @author by nikolai.pashkevich
 */
public enum Quarter {
    FIRST("1"),
    SECOND("2"),
    THIRD("3"),
    FOURTH("4");

    private final String name;

    Quarter(String s) {
        name = s;
    }

    public boolean equalsName(String otherName){
        return (otherName != null) && name.equals(otherName);
    }

    public String toString(){
        return name;
    }
}
