package com.analitics.managerialstaff.backend.service.certification;

import com.analitics.managerialstaff.backend.model.Certification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author by nikolai.pashkevich
 */
public interface CertificationRepository extends CrudRepository<Certification, Long>, JpaSpecificationExecutor<Certification> {
}
