/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.review.dao.hpmImp;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.orm.hibernate3.HibernateCallback;
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
            }
        });

        return null;

    }

    @Override
    public List<Review> getUserProductReviews(final Integer userId, final Integer productId) {
        return getHibernateTemplate().executeFind(new HibernateCallback<List<Review>>() {

            @Override
            public List<Review> doInHibernate(Session sn) throws HibernateException, SQLException {
                Query q = sn.createQuery("from Review where userId = :userId and productId = :productId");
                q.setInteger("userId", userId);
                q.setInteger("productId", productId);
                return q.list();
            }
        });
    }

    @Override
    public List<Review> getAllProductReviews(final Integer productId) {
        return getHibernateTemplate().executeFind(new HibernateCallback<List<Review>>() {

            @Override
            public List<Review> doInHibernate(Session sn) throws HibernateException, SQLException {
                Query q = sn.createQuery("from Review where productId = :productId");
                q.setInteger("productId", productId);
                return q.list();
            }
        });
    }
}
