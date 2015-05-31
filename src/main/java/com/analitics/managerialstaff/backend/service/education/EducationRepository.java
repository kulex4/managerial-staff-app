package com.analitics.managerialstaff.backend.service.education;

import com.analitics.managerialstaff.backend.model.Education;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author by nikolai.pashkevich
 */
public interface EducationRepository extends CrudRepository<Education, Long>, JpaSpecificationExecutor<Education> {
}
