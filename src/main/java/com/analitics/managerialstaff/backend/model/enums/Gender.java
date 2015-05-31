package com.analitics.managerialstaff.backend.model.enums;

/**
 * @author by nikolai.pashkevich
 */
public enum Gender {
    MALE("мужской"),
    FEMALE("женский");

    private final String name;

    Gender(String s) {
        name = s;
    }

    public boolean equalsName(String otherName){
        return (otherName != null) && name.equals(otherName);
    }

    public String toString(){
        return name;
    }
}
