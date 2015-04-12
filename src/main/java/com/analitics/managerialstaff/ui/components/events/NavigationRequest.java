package com.analitics.managerialstaff.ui.components.events;

import lombok.Data;

/**
 * @author by nikolai.pashkevich
 */
@Data
public class NavigationRequest {
    private String navigationState;

    public NavigationRequest(String navigationState) {
        this.navigationState = navigationState;
    }
}
