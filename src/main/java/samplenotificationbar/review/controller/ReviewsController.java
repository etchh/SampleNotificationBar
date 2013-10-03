/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.review.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import samplenotificationbar.product.service.ProductService;
import samplenotificationbar.review.domain.Review;
import samplenotificationbar.review.service.ReviewService;
import samplenotificationbar.user.service.UserService;

/**
 *
 * @author mostafa
 */
@Controller
@RequestMapping("/reviews")
public class ReviewsController {
    

    ReviewService reviewService;
    ProductService productService;
    UserService userService;
    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    
    @RequestMapping("/reviewNotifier.htm")
    public void notifyAllUsers(@RequestParam("userId")Integer userId , @RequestParam("productId") Integer productId,@RequestParam("text")String comment,HttpServletRequest request,HttpServletResponse response){
        Review review = new Review();
        
        review.setComment(comment);
        review.setCommentDate(new Date());
        review.setProduct(productService.getProduct(productId));
        review.setUser(userService.getUser(userId));
        
        reviewService.saveReview(review);
        
    }
    @RequestMapping("/getRecentReviews.htm")
    public void getRecentReviews(@RequestParam("productId") Integer productId ,HttpServletRequest request,HttpServletResponse response){
        
        List<Review> reviews = productService.getRevList();
        for(Review review : reviews){
            System.out.println("New Review from : "+ review.getUser().getName()+" \nOn Product: "+ review.getProduct().getName() + " \nWith comment: "+review.getComment());
        }
        
    }
    
    
}
