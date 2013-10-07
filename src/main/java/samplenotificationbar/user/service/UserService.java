/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import samplenotificationbar.review.domain.Review;
import samplenotificationbar.user.domain.User;

/**
 *
 * @author mostafa
 */
public interface UserService {
    public User getUser(Integer userId);
    
    public List<User> getAllUsers();
    public void setReviews(HashMap<Integer, ArrayList<Review>> reviews) ;
    public HashMap<Integer, ArrayList<Review>> getReviews();
    
    public void setUserProductReviews(Integer productId ,Integer userId,HashMap<Integer, ArrayList<Review>> reviews);
    public ArrayList<Review> getUserProductReviews(Integer ProductId);
    
    
}
