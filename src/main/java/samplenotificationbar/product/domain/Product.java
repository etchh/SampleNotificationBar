/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.product.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author mostafa
 */

@Entity
@Table(name = "product")
public class Product {
    
    Integer productId;
    public Product(){
        
    }
    
}
