package com.dropit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dropit.entities.TimeslotEntity;

@Repository
public interface TimeslotRepository extends JpaRepository<TimeslotEntity, Long> {
}
