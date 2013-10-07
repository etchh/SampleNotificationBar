/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.review.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samplenotificationbar.product.service.ProductService;
import samplenotificationbar.review.dao.ReviewDao;
import samplenotificationbar.review.domain.Review;
import samplenotificationbar.review.event.AddReviewEvent;
import samplenotificationbar.review.service.ReviewService;
import samplenotificationbar.user.service.UserService;

/**
 *
 * @author mostafa
 */
@Service("reviewService")
public class ReviewServiceImp implements ApplicationListener<AddReviewEvent>, ApplicationEventPublisherAware, ReviewService {

    ReviewDao reviewDao;
    ApplicationEventPublisher applicationEventPublisher;
    UserService userService;
    ProductService productService;
    private ArrayList<Review> revList = new ArrayList<Review>();
    private HashMap<Integer, ArrayList<Review>> reviews = new HashMap<Integer, ArrayList<Review>>();

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

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
        revList.add(review);

//        if (productService.getReviews() == null || productService.getReviews().isEmpty()) {
//            HashMap x = new HashMap<Integer, ArrayList<Review>>();
//            ArrayList<Review> y = new ArrayList<Review>();
//            y.add(review);
//            x.put(review.getProduct().getProductId(), y);
//            System.out.println("new HashMap has been created , with product id = "+review.getProduct().getProductId());
//            productService.setReviews(x);
//        } else {
//            if (productService.getReviews().get(review.getProduct().getProductId()) == null ||productService.getReviews().get(review.getProduct().getProductId()).isEmpty() ) {
//                ArrayList<Review> y = new ArrayList<Review>();
//                y.add(review);
//                System.out.println("new ArrayList has been created");
//                productService.getReviews().put(review.getProduct().getProductId(), y);
//            } else {
//                System.out.println("HashMap already created");
//                ArrayList<Review> old = productService.getReviews().get(review.getProduct().getProductId());
//                old.add(review);
//                productService.getReviews().put(review.getProduct().getProductId(),old );
//            }
//
//        }
        if (reviews.isEmpty()) {
            ArrayList<Review> y = new ArrayList<Review>();
            y.add(review);
            reviews.put(review.getProduct().getProductId(), y);
            
        } else {
            if (reviews.get(review.getProduct().getProductId()) != null) {
                ArrayList<Review> productReviewList = reviews.get(review.getProduct().getProductId());
                productReviewList.add(review);
                reviews.put(review.getProduct().getProductId(), productReviewList);
            }

        }
        productService.setReviews(reviews);
        for(Review rev : productService.getReviews().get(review.getProduct().getProductId())){
            System.out.println("before publish" + "\n  user: "+rev.getUser() +"  ,  comment: " + rev.getComment() + "   , product name: " + rev.getProduct().getName());
        }
        

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
    public void onApplicationEvent(AddReviewEvent e) {
        System.out.println("added new review!");
    }

    
}
