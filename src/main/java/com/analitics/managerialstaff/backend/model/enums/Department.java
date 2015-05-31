package com.analitics.managerialstaff.backend.model.enums;

/**
 * @author by nikolai.pashkevich
 */
public enum Department {
    CHIEF_ACCOUNTANT("Главный бухгалтер и его помощники"),
    FINANCIAL_ACCOUNTING_AND_REPORTING("Управление финансового учета и отчетности"),
    COST_ACCOUNTING("Управление производственного учета"),
    TAX_REPORTING("Отдел налоговой отчетности"),
    CONSOLIDATED_FINANCIAL_STATEMENTS("Управление консолидированной финансовой отчетности");

    private final String name;

    Department(String s) {
        name = s;
    }

    public boolean equalsName(String otherName){
        return (otherName != null) && name.equals(otherName);
    }

    public String toString(){
        return name;
    }
}
