/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.review.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;
import samplenotificationbar.product.domain.Product;
import samplenotificationbar.user.domain.User;

/**
 *
 * @author mostafa
 */
@Entity
@Table(name = "review")
public class Review {

    Integer reviewId;
    String comment;
    Date commentDate;
    Set<User> users = new HashSet<User>();
    Product product;

    public Review() {
    }

    public Review(Integer reviewId, String comment, Date commentDate) {
        this.reviewId = reviewId;
        this.comment = comment;
        this.commentDate = commentDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    @Column(name = "comment", nullable = false, length = 45)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "comment_date", nullable = false)
    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
    @ForeignKey(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = true)
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    @ForeignKey(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_d", nullable = true)
    public Set<User> getUsers() {
        return this.users;
    }

    public void setShippingRegion(Set<User> users) {
        this.users = users;
    }
}
