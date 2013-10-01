/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.review.dao.hpmImp;

import samplenotificationbar.review.dao.ReviewDao;
import samplenotificationbar.review.domain.Review;
import samplenotificationbar.util.dao.GenericDao;

/**
 *
 * @author mostafa
 */
public class ReviewDaoImp extends GenericDao<Review> implements ReviewDao{
    
    {
        super.setClass(Review.class);
    }
}
