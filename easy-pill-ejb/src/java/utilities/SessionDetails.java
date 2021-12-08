/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Topsy
 */
@ManagedBean
@SessionScoped
public class SessionDetails implements Serializable {

    private static String userId;
    private static String userEmail;
    private static String userFirstName;
    private static String userLastName;
    private static String userRole;

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        SessionDetails.userId = userId;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmail) {
        SessionDetails.userEmail = userEmail;
    }

    public static String getUserFirstName() {
        return userFirstName;
    }

    public static void setUserFirstName(String userFirstName) {
        SessionDetails.userFirstName = userFirstName;
    }

    public static String getUserLastName() {
        return userLastName;
    }

    public static void setUserLastName(String userLastName) {
        SessionDetails.userLastName = userLastName;
    }

    public static String getUserRole() {
        return userRole;
    }

    public static void setUserRole(String userRole) {
        SessionDetails.userRole = userRole;
    }

   
}
