package com.analitics.managerialstaff.backend.model.enums;

/**
 * @author by nikolai.pashkevich
 */
public enum EducationType {
    ECONOMIC("экономическое"),
    TECHNICAL("техническое"),
    HUMANITARIAN("гуманитарное");

    private final String name;

    EducationType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName){
        return (otherName != null) && name.equals(otherName);
    }

    public String toString(){
        return name;
    }
}
