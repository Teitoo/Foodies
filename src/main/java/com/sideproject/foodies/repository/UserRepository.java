package com.sideproject.foodies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sideproject.foodies.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
