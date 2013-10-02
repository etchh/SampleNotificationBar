/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.review.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import samplenotificationbar.review.dao.ReviewDao;
import samplenotificationbar.review.domain.Review;
import samplenotificationbar.review.event.AddReviewEvent;
import samplenotificationbar.review.service.ReviewService;

/**
 *
 * @author mostafa
 */
@Service("reviewService")
public class ReviewServiceImp implements ApplicationEventPublisherAware ,ReviewService {

    ReviewDao reviewDao;
    ApplicationEventPublisher applicationEventPublisher ;

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
    public void setApplicationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    
    @Override
    public void saveReview(Review review) {
        reviewDao.save(review);
        this.setApplicationEventPublisher(new ApplicationEventPublisher() {
            public void publishEvent(AddReviewEvent ae) {
                System.out.println("Add Review is done ReviewDaoImp");
            }

            @Override
            public void publishEvent(ApplicationEvent ae) {
            }
        });

    }

    @Override
    public Review getReview(Integer reviewId) {
        return reviewDao.get(reviewId);
    }
}
