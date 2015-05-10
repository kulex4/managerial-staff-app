package com.analitics.managerialstaff.backend.service;

import com.analitics.managerialstaff.backend.model.Certification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return certificationRepository.findAll();
    }
}
