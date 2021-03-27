package com.krzysiek.social.repository;

import com.krzysiek.social.model.Subsocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubsocialRepository extends JpaRepository<Subsocial, Long> {

    Optional<Subsocial> findByName(String subsocialName);
}
