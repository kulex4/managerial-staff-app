package com.analitics.managerialstaff.ui.components.events.certifications;

import com.analitics.managerialstaff.ui.view.navigations.certification.dto.CertificationDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by nikolai.pashkevich
 */
@Getter
@Setter
public class CertificationEditEvent {
    private CertificationDTO certification;

    public CertificationEditEvent(CertificationDTO certification) {
        this.certification = certification;
    }
}
