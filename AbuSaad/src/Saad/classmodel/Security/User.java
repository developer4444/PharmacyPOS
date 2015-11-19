/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Saad.classmodel.Security;

import Saad.databaseutilities.DatabaseWrapper;

/**
 *
 * @author Pasha
 */
public class User {
    public String userName;
    public String password;
    public String userGroupName;

    public User(String userLoginName, String userPassword) {
        userName = userLoginName;
        password = userPassword;
        userGroupName = DatabaseWrapper.getUserGroupName(userName);
    }

    public boolean hasRightsOnOperation(String operation)
    {
        return DatabaseWrapper.checkUserGroupOperationRights(userGroupName, operation);
    }

    public boolean login() {
        return DatabaseWrapper.checkUserLogin(userName, password);
    }
}
