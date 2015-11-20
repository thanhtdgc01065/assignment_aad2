/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.view;

import com.thanhtd.controller.DbController;
import com.thanhtd.model.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Thanh Tran
 */
public class MasterPasswordFrame extends javax.swing.JFrame {

    /**
     * Creates new form MasterPasswordFrame
     */
    //Use when create Master Password
    public MasterPasswordFrame(JFrame parent) {
        initComponents();
        setupApp();

        newmpOkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if (String.valueOf(newmpPassTxt.getPassword()).compareTo(Main.AES_PASSWORD_KEY) == 0) {
//                    JOptionPane.showMessageDialog(null, "This Master Password is same with current Master Password.");
//                } else {
                if (String.valueOf(newmpPassTxt.getPassword()).compareTo(String.valueOf(newmpRePassTxt.getPassword())) == 0) {
                    User temp = new User();
                    temp.setUsername("MASTERPASSWORD");
                    //temp.setPassword(String.valueOf(jPasswordField2.getPassword()));
                    temp.setPassword(DigestUtils.md5Hex(String.valueOf(newmpPassTxt.getPassword())));
                    temp.setUrl("MASTERPASSWORD");
                    temp.setNote("MASTERPASSWORD");

                    //Main.AES_PASSWORD = String.valueOf(jPasswordField1.getPassword());
                    Main.AES_PASSWORD_KEY = DigestUtils.md5Hex(String.valueOf(newmpPassTxt.getPassword()));
                    DbController.InsertInfoToDB(temp, true);
                    parent.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Password is not match.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        newmpCloseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    //Use when changing Master Password
//    public MasterPasswordFrame(JFrame parent, DefaultListModel list) {
//        initComponents();
//        setupApp();
//
//        jButton2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (String.valueOf(jPasswordField1.getPassword()).compareTo(String.valueOf(jPasswordField2.getPassword())) == 0) {
//                    if (DigestUtils.md5Hex(String.valueOf(jPasswordField1.getPassword())).compareTo(Main.AES_PASSWORD_KEY) == 0) {
//                        JOptionPane.showMessageDialog(null, "This Master Password is same with current Master Password.\nPlease choose another Master Password.");
//                        //setVisible(false);
//                    } else {
//                        DbController.ClearAllData();
//                        //Set new pass for MASTERPASSWORD
//                        User temp = new User();
//                        temp.setUsername("MASTERPASSWORD");
//                        //temp.setPassword(String.valueOf(jPasswordField1.getPassword()));
//                        temp.setPassword(DigestUtils.md5Hex(String.valueOf(jPasswordField1.getPassword())));
//                        temp.setUrl("MASTERPASSWORD");
//                        temp.setNote("MASTERPASSWORD");
//
//                        //Main.AES_PASSWORD = String.valueOf(jPasswordField1.getPassword());
//                        Main.AES_PASSWORD_KEY = DigestUtils.md5Hex(String.valueOf(jPasswordField1.getPassword()));
//
//                        DbController.InsertInfoToDB(temp, true);
//
//                        //Write all data to DB with new AES_KEY
//                        for (int i = 0; i < list.getSize(); i++) {
//                            User tempInsert = new User();
//                            User tempOld = (User) list.getElementAt(i);
//
//                            tempInsert.setUsername(tempOld.getUsername());
//                            tempInsert.setPassword(tempOld.getPassword());
//                            tempInsert.setUrl(tempOld.getUrl());
//                            tempInsert.setNote(tempOld.getNote());
//                            DbController.InsertInfoToDB(tempInsert);
//                        }
//                        JOptionPane.showMessageDialog(null, "Master password changed.");
//
//                        parent.setVisible(true);
//                        setVisible(false);
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "Password is not match.", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//
//            }
//        });
//
//        jButton1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                setVisible(false);
//            }
//        });
//    }
    private void setupApp() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        newmpCloseBtn = new javax.swing.JButton();
        newmpOkBtn = new javax.swing.JButton();
        newmpPassTxt = new javax.swing.JPasswordField();
        newmpRePassTxt = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create Master Password");

        jLabel1.setText("Password");

        jLabel2.setText("Retype password");

        newmpCloseBtn.setText("Close");

        newmpOkBtn.setText("OK");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 156, Short.MAX_VALUE)
                        .addComponent(newmpOkBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newmpCloseBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newmpPassTxt)
                            .addComponent(newmpRePassTxt))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(newmpPassTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(newmpRePassTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newmpCloseBtn)
                    .addComponent(newmpOkBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton newmpCloseBtn;
    private javax.swing.JButton newmpOkBtn;
    private javax.swing.JPasswordField newmpPassTxt;
    private javax.swing.JPasswordField newmpRePassTxt;
    // End of variables declaration//GEN-END:variables
}
