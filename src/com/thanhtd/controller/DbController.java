/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.controller;

import com.thanhtd.model.User;
import com.thanhtd.view.Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Thanh Tran
 */
public class DbController {

    public static Connection conn = null;

    public static void ConnectToDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:data\\MyPassApp.sqlite";
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println("Connect to DB successfuly.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error to connect to DB.");
        }
    }

    public static User GetMasterpassFromDB() {
        try {
            String sql = "SELECT * FROM [MyApp] WHERE Username='MASTERPASSWORD' "
                    + "AND Url='MASTERPASSWORD' AND Note='MASTERPASSWORD'";
            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery(sql);
            if (result == null) {
                System.out.println("Not have Master Password.");
                return null;
            } else {
                while (result.next()) {
                    User temp = new User();
                    temp.setUsername(result.getString(1));
                    temp.setPassword(EncryptDecrypt.DecryptString(result.getString(2)));
                    temp.setUrl(result.getString(3));
                    temp.setNote(result.getString(4));
                    System.out.println("Get Master password from DB successfully.");
                    return temp;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Can not get Master password from DB.");
        }
        return null;
    }

    public static void ReadFromDB(DefaultListModel list) {
        try {
            String sql = "SELECT * FROM [MyApp]";
            Statement statement = conn.createStatement();
            int count = 0;

            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                if (count == 0) {
                    count++;
                    continue;
                }
//                String dbUser = result.getString(1);
//                String dbPass = EncryptDecrypt.DecryptString(result.getString(2), Main.AES_PASSWORD_KEY);
//                String dbUrl = result.getString(3);
//                String dbNote = result.getString(4);
                
                String dbUser = EncryptDecrypt.DecryptString(result.getString(1), Main.AES_PASSWORD_KEY);
                String dbPass = EncryptDecrypt.DecryptString(result.getString(2), Main.AES_PASSWORD_KEY);
                String dbUrl = EncryptDecrypt.DecryptString(result.getString(3), Main.AES_PASSWORD_KEY);
                String dbNote = EncryptDecrypt.DecryptString(result.getString(4), Main.AES_PASSWORD_KEY);

                list.addElement(new User(dbUser, dbPass, dbUrl, dbNote));
            }
            System.out.println("Read all data from DB.");
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error when read data from DB.");
        }
    }

    public static void InsertInfoToDB(User i) {
        InsertInfoToDB(i, false);
    }

    public static void InsertInfoToDB(User i, boolean isMP) {
        try {
//            String sql = "INSERT INTO [MyApp](Username,Password,Url,Note) VALUES (?,?,?,?)";
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, i.getUsername());
//            if (isMP) {
//                statement.setString(2, EncryptDecrypt.EncryptString(i.getPassword()));
//            } else {
//                statement.setString(2, EncryptDecrypt.EncryptString(i.getPassword(), Main.AES_PASSWORD_KEY));
//            }
//            statement.setString(3, i.getUrl());
//            statement.setString(4, i.getNote());
//            int row = statement.executeUpdate();
//            if (row == 1) {
//                System.out.println("Insert into DB successfully.");
//            }

            String sql = "INSERT INTO [MyApp](Username,Password,Url,Note) VALUES (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            if (isMP) {
                statement.setString(1, i.getUsername());
                statement.setString(2, EncryptDecrypt.EncryptString(i.getPassword()));
                statement.setString(3, i.getUrl());
                statement.setString(4, i.getNote());
            } else {
                statement.setString(1, EncryptDecrypt.EncryptString(i.getUsername(), Main.AES_PASSWORD_KEY));
                statement.setString(2, EncryptDecrypt.EncryptString(i.getPassword(), Main.AES_PASSWORD_KEY));
                statement.setString(3, EncryptDecrypt.EncryptString(i.getUrl(), Main.AES_PASSWORD_KEY));
                statement.setString(4, EncryptDecrypt.EncryptString(i.getNote(), Main.AES_PASSWORD_KEY));
            }

            int row = statement.executeUpdate();
            if (row == 1) {
                System.out.println("Insert into DB successfully.");
            }

        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Insert failed.");
        }
    }

    public static void UpdateInfoToDB(User oldUser, User newUser) {
        try {
            String sql = "UPDATE [MyApp] SET Username=?,Password=?,Url=?,Note=? WHERE Username=? AND Url=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, newUser.getUsername());
            //statement.setString(2, EncryptDecrypt.EncryptString(newUser.getPassword()));
            statement.setString(2, EncryptDecrypt.EncryptString(newUser.getPassword(), Main.AES_PASSWORD_KEY));
            statement.setString(3, newUser.getUrl());
            statement.setString(4, newUser.getNote());
            statement.setString(5, oldUser.getUsername());
            statement.setString(6, oldUser.getUrl());
            int row = statement.executeUpdate();
            if (row == 1) {
                System.out.println("Update to DB successfully.");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Update failed.");
        }
    }

    public static void ClearAllData() {
        try {
            String sql = "DELETE FROM [MyApp]";
            Statement statement = conn.createStatement();
            int row = statement.executeUpdate(sql);
            if (row != 0) {
                System.out.println("Delete data successfully.");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Delete data unsuccessfully.");
        }
    }

    public static void RemoveInfoFromDB(User i) {
        try {
            String sql = "DELETE FROM [MyApp] WHERE Username=? AND Url=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, i.getUsername());
            statement.setString(2, i.getUrl());
            int row = statement.executeUpdate();
            if (row == 1) {
                System.out.println("Delete successfully.");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed to delete.");
        }
    }
}
