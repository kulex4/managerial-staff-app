package com.analitics.managerialstaff.backend.service;

import com.analitics.managerialstaff.backend.model.Certification;

/**
 * @author by nikolai.pashkevich
 */
public interface CertificationService {
    void saveOrUpdate(Certification certification);
    void remove(Certification certification);
    Iterable<Certification> findCertifications();
}
