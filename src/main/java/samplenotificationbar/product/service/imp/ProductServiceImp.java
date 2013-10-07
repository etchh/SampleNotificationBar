/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.product.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import samplenotificationbar.product.dao.ProductDao;
import samplenotificationbar.product.domain.Product;
import samplenotificationbar.product.event.GetReviewEvent;
import samplenotificationbar.product.service.ProductService;
import samplenotificationbar.review.domain.Review;
import samplenotificationbar.user.service.UserService;

/**
 *
 * @author mostafa
 */
@Service("productService")
public class ProductServiceImp implements ApplicationListener<GetReviewEvent>, ApplicationEventPublisherAware, ProductService {

    ProductDao productDao;
    UserService userService;
    ApplicationEventPublisher applicationEventPublisher;
    HashMap<Integer, ArrayList<Review>> reviews = new HashMap<Integer, ArrayList<Review>>();
    ArrayList<Review> revList ;
    Product product;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    
    
    @Override
    public HashMap<Integer, ArrayList<Review>> getReviews() {
        return reviews;
    }

    @Override
    public void setReviews(HashMap<Integer, ArrayList<Review>> reviews) {
        this.reviews = reviews;
    }

    @Override
    public ArrayList<Review> getRevList() {

        return revList;
    }

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public Product getProduct(Integer productId) {
        return productDao.get(productId);
    }

    @Override
    public void onApplicationEvent(GetReviewEvent e) {
        if(revList != null ) {
            revList.clear();
            reviews.remove(product.getProductId());
            product = null;
        }
        System.out.println("in productService !");



    }

    @Override
    public ArrayList<Review> getRecentReviews(Integer productId) {
        this.product = productDao.get(productId);
        revList = (reviews.get(productId) == null ) ? null : (ArrayList<Review>) reviews.get(productId).clone();
        ArrayList<Review> newList = (revList == null ) ? null : (ArrayList<Review>) revList .clone();
        

//        ArrayList<Review> newList = reviews.remove(productId);
        this.publishGetReviewEvent();
//        reviews.remove(this);
        return newList;
    }

    public void publishGetReviewEvent() {
        this.applicationEventPublisher.publishEvent(new GetReviewEvent(this, this.getClass()));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
