/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.product.dao;

import java.util.List;
import samplenotificationbar.product.domain.Product;

/**
 *
 * @author mostafa
 */
public interface ProductDao {
    
    public List<Product> getAllProducts();
    
}
