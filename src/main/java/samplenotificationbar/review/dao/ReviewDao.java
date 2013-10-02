/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.review.dao;

import java.util.List;
import samplenotificationbar.review.domain.Review;

/**
 *
 * @author mostafa
 */
public interface ReviewDao {
    public Integer saveReview(Review review);
    
    public List<Review> getUserProductReviews(Integer userId , Integer productId);
    
    public List<Review> getAllProductReviews(Integer productId);
}
