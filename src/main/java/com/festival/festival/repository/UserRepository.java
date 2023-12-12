package com.festival.festival.repository;

import com.festival.festival.entity.User;
import com.festival.festival.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom {
}
