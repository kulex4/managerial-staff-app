package com.analitics.managerialstaff.ui.components.events.certifications;

import com.analitics.managerialstaff.backend.model.enums.CertificationYear;
import com.analitics.managerialstaff.backend.model.enums.Quarter;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by nikolai.pashkevich
 */
@Getter
@Setter
public class SearchParametersChangedEvent {
    private CertificationYear year;
    private Quarter quarter;

    public SearchParametersChangedEvent(CertificationYear year, Quarter quarter) {
        this.year = year;
        this.quarter = quarter;
    }
}
