package com.krzysiek.social.repository;

import com.krzysiek.social.model.Subsocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubsocialRepository extends JpaRepository<Subsocial, Long> {
}
