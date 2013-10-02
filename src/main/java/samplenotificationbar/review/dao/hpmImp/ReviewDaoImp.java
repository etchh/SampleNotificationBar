/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.review.dao.hpmImp;

import java.util.HashSet;
import java.util.Set;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import samplenotificationbar.review.dao.ReviewDao;
import samplenotificationbar.review.domain.Review;
import samplenotificationbar.review.event.AddReviewEvent;
import samplenotificationbar.util.dao.GenericDao;

/**
 *
 * @author mostafa
 */
public class ReviewDaoImp extends GenericDao<Review> implements ReviewDao {

    {
        super.setClass(Review.class);
    }

    @Override
    public Integer saveReview(Review review) {
        super.save(review);
        review.setApplicationEventPublisher(new ApplicationEventPublisher() {
            public void publishEvent(AddReviewEvent ae) {
                System.out.println("Add Review is done ReviewDaoImp");
            }

            @Override
            public void publishEvent(ApplicationEvent ae) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        return null;

    }
}
