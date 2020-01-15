package com.training.api.model.area;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Area}.
 */
@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {

    /**
     * Find list address by post code
     * @param postCode code of post at address
     * @return address of post coe
     */
    List<Area> findByPostPostCode(String postCode);
}
