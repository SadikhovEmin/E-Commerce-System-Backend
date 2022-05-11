package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.Comment;
import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.web3j.abi.datatypes.Int;

import java.util.List;

@RestController
@RequestMapping(path = "/comments")
@CrossOrigin
public class CommentController {

    private final CommentService service;

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping
    public void addComment(@RequestBody Comment comment) {
        service.saveComment(comment);
    }

    @GetMapping(path = "/{id}")
    public List<Comment> getCommentById(@PathVariable Integer id) {
        return service.getCommentById(id);
    }

    @PutMapping("/customer/{customerID}/product/{productId}/comment/{commentId}")
    public Comment putCustomerForComment(
            @PathVariable Integer customerID,
            @PathVariable Integer commentId,
            @PathVariable Integer productId
    ) {
        return service.addCommentWithCustomer(customerID, commentId, productId);
    }
}
