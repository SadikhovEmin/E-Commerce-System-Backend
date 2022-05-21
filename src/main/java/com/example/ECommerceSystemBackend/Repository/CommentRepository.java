package com.example.ECommerceSystemBackend.Repository;

import com.example.ECommerceSystemBackend.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> getAllByProductId(Integer id);
}
