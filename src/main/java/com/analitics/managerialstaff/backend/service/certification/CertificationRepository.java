package com.analitics.managerialstaff.backend.service.certification;

import com.analitics.managerialstaff.backend.model.Certification;
import com.analitics.managerialstaff.backend.model.enums.CertificationYear;
import com.analitics.managerialstaff.backend.model.enums.Quarter;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author by nikolai.pashkevich
 */
public interface CertificationRepository extends CrudRepository<Certification, Long>, JpaSpecificationExecutor<Certification> {

    @Query("FROM Certification WHERE year = ?1")
    Iterable<Certification> findByYear(CertificationYear year);

    @Query("FROM Certification WHERE quarter = ?1")
    Iterable<Certification> findByQuarter(Quarter quarter);

    @Query("FROM Certification WHERE year = ?1 AND quarter = ?2")
    Iterable<Certification> findByYearAndQuarter(CertificationYear year, Quarter quarter);
}
