package com.analitics.managerialstaff.backend.service.education;

import com.analitics.managerialstaff.backend.model.Education;

/**
 * @author by nikolai.pashkevich
 */
public interface EducationService {
    void saveOrUpdate(Education education);
    void remove(Education education);
}
