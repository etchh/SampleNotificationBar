/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import samplenotificationbar.review.domain.Review;
import samplenotificationbar.review.event.AddReviewEvent;

/**
 *
 * @author mostafa
 */
@Controller
public class ReviewsController {
    

    
    
    
    
    public void notifyObservers(){
        Review review = new Review();
        review.setApplicationEventPublisher( new ApplicationEventPublisher() {
            
            public void publishEvent(AddReviewEvent ae) {
                System.out.println("new review has been added!");
            }

            @Override
            public void publishEvent(ApplicationEvent ae) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        });
        
    }
    
    
}
