/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.product.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import samplenotificationbar.product.dao.ProductDao;
import samplenotificationbar.product.domain.Product;
import samplenotificationbar.product.service.ProductService;

/**
 *
 * @author mostafa
 */
@Service("productService")
public class ProductServiceImp implements ProductService{

    ProductDao productDao;

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
    
}
