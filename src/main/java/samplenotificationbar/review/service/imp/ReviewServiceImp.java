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
import org.springframework.transaction.annotation.Transactional;
import samplenotificationbar.review.dao.ReviewDao;
import samplenotificationbar.review.domain.Review;
import samplenotificationbar.review.event.AddReviewEvent;
import samplenotificationbar.review.event.GetReviewEvent;
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
    private List reviews = new ArrayList();

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
    @Transactional
    public void saveReview(Review review) {
        reviewDao.save(review);
        reviews.add(review);
        this.publishAddReviewEvent();




    }
    

    public void publishAddReviewEvent() {
        this.applicationEventPublisher.publishEvent(new AddReviewEvent(this, this.getClass()));
    }
    public void publishGetReviewEvent() {
        this.applicationEventPublisher.publishEvent(new GetReviewEvent(this, this.getClass()));
    }

    @Override
    public Review getReview(Integer reviewId) {
        return reviewDao.get(reviewId);
    }


    @Override
    public void onApplicationEvent(GetReviewEvent e) {
        reviews.clear();
    }

    @Override
    public List getRecentReviews() {
        List revList = new ArrayList();;
        for(Object r : reviews){
            revList.add((Review)r);
        }
        this.publishGetReviewEvent();
        return revList;
    }
}
