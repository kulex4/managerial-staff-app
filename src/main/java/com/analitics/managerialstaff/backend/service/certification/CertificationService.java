package com.analitics.managerialstaff.backend.service.certification;

import com.analitics.managerialstaff.backend.model.Certification;
import com.analitics.managerialstaff.backend.model.enums.CertificationYear;
import com.analitics.managerialstaff.backend.model.enums.Quarter;

/**
 * @author by nikolai.pashkevich
 */
public interface CertificationService {
    void saveOrUpdate(Certification certification);
    void remove(Certification certification);
    Iterable<Certification> findCertifications();
    Certification findById(Long id);
    Iterable<Certification> findByYearAndQuarter(CertificationYear year, Quarter quarter);
}
