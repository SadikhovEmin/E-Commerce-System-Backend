package com.example.ECommerceSystemBackend.Model.DTO;


public class CommentDTO {

    public String comment;
    public Integer customerId;
    public Integer productId;
    public Double review;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getReview() {
        return review;
    }

    public void setReview(Double review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "comment='" + comment + '\'' +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", review=" + review +
                '}';
    }
}
