package com.sideproject.foodies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sideproject.foodies.beans.Cuisine;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long>{

}
