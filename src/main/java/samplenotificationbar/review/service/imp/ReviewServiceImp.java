/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.review.service.imp;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import samplenotificationbar.review.dao.ReviewDao;
import samplenotificationbar.review.domain.Review;
import samplenotificationbar.review.event.AddReviewEvent;
import samplenotificationbar.product.event.GetReviewEvent;
import samplenotificationbar.review.service.ReviewService;
import samplenotificationbar.user.service.UserService;

/**
 *
 * @author mostafa
 */
@Service("reviewService")
public class ReviewServiceImp implements ApplicationListener<GetReviewEvent>, ApplicationEventPublisherAware, ReviewService {

    ReviewDao reviewDao;
    ApplicationEventPublisher applicationEventPublisher;
    UserService userService;
    private ArrayList<Review> reviews = new ArrayList<Review>();
    
    
    
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setReviewDao(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public List<Review> getAllReviewsForProduct(Integer productId) {
        return reviewDao.getAllProductReviews(productId);
    }

    @Override
    public List<Review> getUserProductReviews(Integer userId, Integer productId) {
        return reviewDao.getUserProductReviews(userId, productId);
    }

    @Autowired
    @Override
    public void setApplicationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void saveReview(Review review) {
        reviewDao.save(review);
        reviews.add(review);
        this.publishAddReviewEvent();




    }


    public void publishAddReviewEvent() {
        this.applicationEventPublisher.publishEvent(new AddReviewEvent(this, this.getClass()));
    }
   

    @Override
    public Review getReview(Integer reviewId) {
        return reviewDao.get(reviewId);
    }


    @Override
    public void onApplicationEvent(GetReviewEvent e) {
        reviews.clear();
    }

    
    
    


    
}
