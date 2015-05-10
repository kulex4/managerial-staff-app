package com.analitics.managerialstaff.backend.model.enums;

/**
 * @author by nikolai.pashkevich
 */
public enum University {
    BSUIR("БГУИР"),
    BNTU("БНТУ");

    private final String name;

    University(String s) {
        name = s;
    }

    public boolean equalsName(String otherName){
        return (otherName != null) && name.equals(otherName);
    }

    public String toString(){
        return name;
    }
}
