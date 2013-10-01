/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.user.dao.hpmImp;

import samplenotificationbar.user.dao.UserDao;
import samplenotificationbar.user.domain.User;
import samplenotificationbar.util.dao.GenericDao;

/**
 *
 * @author mostafa
 */
public class UserDaoImp extends GenericDao<User> implements UserDao{
    
    {
        super.setClass(User.class);
    }
}
