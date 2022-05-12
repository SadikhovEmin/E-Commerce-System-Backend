package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.Comment;
import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.DTO.CommentDTO;
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

    public Comment saveComment(CommentDTO commentDTO) {
        Comment comment = new Comment(commentDTO.comment);
        Customer customer = customerRepository.getCustomerById(commentDTO.customerId);
        Product product = productRepository.getProductById(commentDTO.productId);

        comment.addComment(customer, product);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentById(Integer id) {
        return commentRepository.getAllByProductId(id);
    }

    public Integer getMaxId() { return commentRepository.getMaxId(); }

    public Comment addCommentWithCustomer(Integer customerID, Integer commentId, Integer productId) {
        Comment comment = commentRepository.findById(commentId).get();
        Customer customer = customerRepository.findById(customerID).get();
        Product product = productRepository.findById(productId).get();

        comment.addComment(customer, product);
        return commentRepository.save(comment);
    }
}
