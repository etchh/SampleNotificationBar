/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.product.dao.hpmImp;

import samplenotificationbar.product.dao.ProductDao;
import samplenotificationbar.product.domain.Product;
import samplenotificationbar.util.dao.GenericDao;

/**
 *
 * @author mostafa
 */
public class ProductDaoImp extends GenericDao<Product> implements ProductDao{
    
    {
        super.setClass(Product.class);
    }
}
