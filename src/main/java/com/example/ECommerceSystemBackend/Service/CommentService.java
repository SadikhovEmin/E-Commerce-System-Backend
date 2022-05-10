package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.Comment;
import com.example.ECommerceSystemBackend.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository repository;

    @Autowired
    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public Comment saveComment(Comment comment) {
        return repository.save(comment);
    }

    public List<Comment> getCommentById(Integer id) {
        return repository.getAllByProductId(id);
    }
}
