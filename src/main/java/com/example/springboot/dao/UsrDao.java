package com.example.springboot.dao;

import com.example.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrDao extends JpaRepository<User, Long> {
}
