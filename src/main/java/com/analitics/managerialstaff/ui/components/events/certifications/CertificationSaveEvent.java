package com.analitics.managerialstaff.ui.components.events.certifications;

import com.analitics.managerialstaff.backend.model.Certification;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by nikolai.pashkevich
 */
@Getter
@Setter
public class CertificationSaveEvent {
    private Certification certification;
    private boolean isNew;

    public CertificationSaveEvent(Certification certification, boolean isNew) {
        this.certification = certification;
        this.isNew = isNew;
    }
}
