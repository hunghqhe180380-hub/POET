/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hung2
 */
public class UserDAO extends DBContext {

    protected PreparedStatement statement;
    protected ResultSet resultSet;

    // register => confirm email
    // login => only allow login if email is confirmed 
    //login
    public boolean isExistAccount(String email, String password) {
        try {
            String sql = "select  [Email], [PasswordHash]\n"
                    + "from [dbo].[AspNetUsers]\n"
                    + "where [Email] = ? ";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, email);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getObject("Email").equals(email)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
