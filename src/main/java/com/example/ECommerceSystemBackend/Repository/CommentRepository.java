package com.example.ECommerceSystemBackend.Repository;

import com.example.ECommerceSystemBackend.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
