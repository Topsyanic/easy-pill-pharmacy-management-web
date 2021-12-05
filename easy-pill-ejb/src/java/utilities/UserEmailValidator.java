/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entities.User;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import model.UserFacade;

/**
 *
 * @author Topsy
 */
@Stateless
@LocalBean
public class UserEmailValidator implements EmailValidator {

    @EJB
    private UserFacade userFacade;
    

    @Override
    public boolean emailExists(String email) {
        List<User> usersList = userFacade.getUsersByEmail(email);
        if (usersList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
