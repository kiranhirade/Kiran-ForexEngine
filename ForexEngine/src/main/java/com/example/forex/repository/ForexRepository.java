package com.example.forex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forex.model.Forex;


@Repository
public interface ForexRepository extends JpaRepository<Forex, Long> {

}
