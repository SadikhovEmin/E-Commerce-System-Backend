package com.example.ECommerceSystemBackend.Model;


import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "CONTENT")
    private String content;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "Product_ID",
            referencedColumnName = "id"
    )
    private Product product;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "Customer_ID",
            referencedColumnName = "id"
    )
    private Customer customer;

    public Comment() {

    }

    public Comment(String content) {
        this.content = content;
    }

    public Comment(String content, Product product, Customer customer) {
        this.content = content;
        this.product = product;
        this.customer = customer;
    }

    public void addComment(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", product=" + product +
                ", customer=" + customer +
                '}';
    }
}
