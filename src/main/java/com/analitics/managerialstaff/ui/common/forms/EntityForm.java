package com.analitics.managerialstaff.ui.common.forms;

/**
 * @author by nikolai.pashkevich
 */
public interface EntityForm<ENTITY> {
    void setFormEntity(ENTITY entity);
    void openInModalWindow();
}
