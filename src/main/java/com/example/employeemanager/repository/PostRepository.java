package com.example.employeemanager.repository;

import com.example.employeemanager.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
