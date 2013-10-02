/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samplenotificationbar.user.dao;

import java.util.List;
import samplenotificationbar.user.domain.User;

/**
 *
 * @author mostafa
 */
public interface UserDao {
    
    public List<User> getAllUsers();
    
}
