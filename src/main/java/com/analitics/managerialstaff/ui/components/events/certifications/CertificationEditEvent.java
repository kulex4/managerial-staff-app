package com.analitics.managerialstaff.ui.components.events.certifications;

import com.analitics.managerialstaff.backend.model.Certification;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by nikolai.pashkevich
 */
@Getter
@Setter
public class CertificationEditEvent {
    private Certification certification;

    public CertificationEditEvent(Certification certification) {
        this.certification = certification;
    }
}
