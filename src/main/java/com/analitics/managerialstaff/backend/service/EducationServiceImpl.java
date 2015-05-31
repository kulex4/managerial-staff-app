package com.analitics.managerialstaff.backend.service;

import com.analitics.managerialstaff.backend.model.Education;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author by nikolai.pashkevich
 */
@Transactional
@Service("educationService")
public class EducationServiceImpl implements EducationService {

    private EducationRepository educationRepository;

    @Autowired
    public EducationServiceImpl(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @Override
    public void saveOrUpdate(Education education) {
        educationRepository.save(education);
    }

    @Override
    public void remove(Education education) {
        educationRepository.delete(education);
    }
}
