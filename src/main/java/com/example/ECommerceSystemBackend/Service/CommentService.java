package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.Comment;
import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Repository.CommentRepository;
import com.example.ECommerceSystemBackend.Repository.CustomerRepository;
import com.example.ECommerceSystemBackend.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CommentService(CommentRepository repository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.commentRepository = repository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentById(Integer id) {
        return commentRepository.getAllByProductId(id);
    }

    public Comment addCommentWithCustomer(Integer customerID, Integer commentId, Integer productId) {
        Comment comment = commentRepository.findById(commentId).get();
        Customer customer = customerRepository.findById(customerID).get();
        Product product = productRepository.findById(productId).get();

        comment.addComment(customer, product);
        return commentRepository.save(comment);
    }
}
