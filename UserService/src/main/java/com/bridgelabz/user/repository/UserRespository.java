package com.bridgelabz.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.user.model.User;

@Repository
public interface UserRespository extends JpaRepository<User, Long>{

	Optional<User> findByUserEmail(String userEmail);

}
