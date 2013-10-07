/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.user.service.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import samplenotificationbar.product.event.GetReviewEvent;
import samplenotificationbar.product.service.ProductService;
import samplenotificationbar.review.domain.Review;
import samplenotificationbar.review.service.ReviewService;
import samplenotificationbar.user.dao.UserDao;
import samplenotificationbar.user.domain.User;
import samplenotificationbar.user.service.UserService;

/**
 *
 * @author mostafa
 */
@Service("userService")
public class UserServiceImp implements UserService , ApplicationListener<GetReviewEvent>{

    UserDao userDao;
    ProductService productService;
    ReviewService reviewService;
    HashMap<Integer , ArrayList<Review>> reviews = new HashMap<Integer , ArrayList<Review>>();
    ArrayList<Review> userReviews = new ArrayList<Review>();

    public HashMap<Integer, ArrayList<Review>> getReviews() {
        return reviews;
    }

    public void setReviews(HashMap<Integer, ArrayList<Review>> reviews) {
        this.reviews = reviews;
    }
    
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    
    
    
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUser(Integer userId) {
        return userDao.get(userId);
    }

    public void onApplicationEvent(GetReviewEvent e ) {
        

    }

    @Override
    public void setUserProductReviews(Integer productId , Integer userId , HashMap<Integer, ArrayList<Review>> reviews) {
        for(Review rev : reviews.get(productId)){
            if(rev.getUser().getUserId().equals(userId)){
                reviews.put(productId , reviews.get(productId));
            }
        }
    }

    @Override
    public ArrayList<Review> getUserProductReviews(Integer productId ) {
        return this.reviews.get(productId);
    }

    

}
