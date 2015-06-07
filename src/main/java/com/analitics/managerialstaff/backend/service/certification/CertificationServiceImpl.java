package com.analitics.managerialstaff.backend.service.certification;

import com.analitics.managerialstaff.backend.model.Certification;
import com.analitics.managerialstaff.backend.model.enums.CertificationYear;
import com.analitics.managerialstaff.backend.model.enums.Quarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * @author by nikolai.pashkevich
 */
@Transactional
@Service("certificationService")
public class CertificationServiceImpl implements CertificationService {

    private CertificationRepository certificationRepository;

    @Autowired
    public CertificationServiceImpl(CertificationRepository certificationRepository) {
        this.certificationRepository = certificationRepository;
    }

    @Override
    public void saveOrUpdate(Certification certification) {
        certificationRepository.save(certification);
    }

    @Override
    public void remove(Certification certification) {
        certificationRepository.delete(certification);
    }

    @Override
    public Iterable<Certification> findCertifications() {
        Iterable<Certification> certifications = certificationRepository.findAll();
        certifications.forEach(certification -> certification.getEmployee().getEducations().size());
        return certifications;
    }

    @Override
    public Certification findById(Long id) {
        Certification certification = certificationRepository.findOne(id);
        certification.getEmployee().getCertifications().size();
        return certification;
    }

    @Override
    public Iterable<Certification> findByYearAndQuarter(CertificationYear year, Quarter quarter) {
        if (year != null && quarter != null) {
            Iterable<Certification> certifications = certificationRepository.findByYearAndQuarter(year, quarter);
            certifications.forEach(certification -> certification.getEmployee().getEducations().size());
            return certifications;
        } else  if (year != null) {
            Iterable<Certification> certifications = certificationRepository.findByYear(year);
            certifications.forEach(certification -> certification.getEmployee().getEducations().size());
            return certifications;
        } else if (quarter != null) {
            Iterable<Certification> certifications = certificationRepository.findByQuarter(quarter);
            certifications.forEach(certification -> certification.getEmployee().getEducations().size());
            return certifications;
        } else {
            return Collections.emptyList();
        }
    }
}
