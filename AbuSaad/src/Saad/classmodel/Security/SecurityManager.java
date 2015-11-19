/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Saad.classmodel.Security;

import Saad.databaseutilities.DatabaseWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pasha
 */
public class SecurityManager {

    public static boolean addSecurityPrivilegesforUserGroup(String userGroupName, List<String> list) {
        boolean returnValue = true;
        for(String securityPrivilege:list)
        {
            boolean addPrivilege = DatabaseWrapper.addSecurityPrivilegeForGroup(userGroupName, securityPrivilege);
            if(false == addPrivilege)
            {
                returnValue = false;
            }
        }
        return returnValue;
    }

    public static List<String> getListOfUsers() {
        return DatabaseWrapper.getListOfUsers();
    }

    public static boolean removeUser(String userToRemove) {
        return DatabaseWrapper.removeUser(userToRemove);
    }

    public static List<String> getListOfUserGroups() {
        return DatabaseWrapper.getListOfUserGroups();
    }

    public static boolean removeUserGroup(String userGroupToRemove) {
        boolean resultRemoveUserGroupFromUserGroups = DatabaseWrapper.removeUserGroupFromUserGroups(userGroupToRemove);
        boolean resultRemoveGroupFromUsers = DatabaseWrapper.removeUsersWithinUserGroup(userGroupToRemove);

        if (resultRemoveUserGroupFromUserGroups && resultRemoveGroupFromUsers)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //public enum SecuredOperations{AddUpdateStock, LaunchSoftware, ProcessReturns, ViewReports};
    public static User currentlyLoggedUser;

    public static User getCurrentlyLoggedUser()
    {
        return currentlyLoggedUser;
    }

    public static void setCurrentlyLoggedInUser(User user)
    {
        currentlyLoggedUser = user;
    }

    public static ArrayList<String> getListOfSecurityGroups()
    {
        return DatabaseWrapper.getSecurityGroups();
    }

}
