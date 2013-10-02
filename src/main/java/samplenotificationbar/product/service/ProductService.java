/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.product.service;

import java.util.List;
import samplenotificationbar.product.domain.Product;

/**
 *
 * @author mostafa
 */
public interface ProductService {
    public Product getProduct(Integer productId);
    
    public List<Product> getAllProducts();
    
}
