package com.analitics.managerialstaff.backend.model.enums;

/**
 * @author by nikolai.pashkevich
 */
public enum CertificationYear {
    YEAR_2015("2015"),
    YEAR_2014("2014"),
    YEAR_2013("2013"),
    YEAR_2012("2012");

    private final String name;

    CertificationYear(String s) {
        name = s;
    }

    public boolean equalsName(String otherName){
        return (otherName != null) && name.equals(otherName);
    }

    public String toString(){
        return name;
    }
}
