/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatroomPackage;

import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import static java.awt.Color.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import javax.imageio.*;

/**
 *
 * @author Ryan1
 */
public class MainMenu1
{
    private Connection conn = null;
    private PreparedStatement pst = null;
    
    private JFrame mainmenuFrame = new JFrame("Main Menu");
    private JButton creategroupButton = new JButton();
    private JButton joingroupButton = new JButton();
    private JLabel titleLabel = new JLabel();
    
    private JPanel createJoingroupPanel = new JPanel();
    private JPanelGradient northPanel = new JPanelGradient();
    private JPanelGradient centerPanel = new JPanelGradient();
    
    private JLabel usernameLabel = new JLabel();
    private JTextField usernameField = new JTextField(8);
    private JLabel enternameLabel = new JLabel();
    private JTextField nameField = new JTextField(5);
    private JLabel enterpasswordLabel = new JLabel();
    private JPasswordField passwordField = new JPasswordField(8);


    
    public MainMenu1()
    {
        mainmenuFrame.setLayout(new BorderLayout());
        mainmenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainmenuFrame.setPreferredSize(new Dimension (1000,800));
        mainmenuFrame.setResizable(false);
        
        mainmenuFrame.add(northPanel, BorderLayout.NORTH);
        mainmenuFrame.add(centerPanel, BorderLayout.CENTER);
        
        setLocationAndSize();
        mainMenuComponents();
        setFont();
        setActionListener();
      
        mainmenuFrame.pack();
        mainmenuFrame.setLocationRelativeTo(null);
        mainmenuFrame.setVisible(true);
    }
    
    private void setLocationAndSize()
    {   
        centerPanel.setLayout(null); 
        creategroupButton.setBounds(380,320,250,100);
        joingroupButton.setBounds(380,200,250,100);
    }
    
    private void mainMenuComponents()
    {
        northPanel.add(titleLabel);
        
        centerPanel.add(joingroupButton);
        centerPanel.add(creategroupButton);
        
        createJoingroupPanel.add(usernameLabel);
        createJoingroupPanel.add(usernameField);
        createJoingroupPanel.add(enternameLabel);
        createJoingroupPanel.add(nameField);
        createJoingroupPanel.add(enterpasswordLabel);
        createJoingroupPanel.add(passwordField);
    }
    
    private void setFont()
    {
        titleLabel.setFont(new Font("SansSerif Plain", Font.BOLD, 55));
        titleLabel.setText("Webshare");
        creategroupButton.setFont(new Font("SansSerif Plain", Font.BOLD, 20));
        creategroupButton.setText("Create a group chat");
        
        joingroupButton.setFont(new Font("SansSerif Plain", Font.BOLD, 20));
        joingroupButton.setText("Join a group chat");
                      
        enternameLabel.setText("Enter the name");
        enterpasswordLabel.setText("Enter password");
        usernameLabel.setText("Enter a username");
    }
    
    private void setActionListener()
    {
        creategroupButton.addActionListener(new CreateGroupHandler());
        joingroupButton.addActionListener(new JoinGroupHandler());
    }

    
    private class CreateGroupHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
           int result = JOptionPane.showConfirmDialog(null, createJoingroupPanel,
                   "Please enter the name and passcode of the group you want to create",
                   JOptionPane.OK_CANCEL_OPTION);
           
           if (result == JOptionPane.OK_OPTION){
                try {
                String query = "INSERT INTO `groupchats`(`Group_name`, `Group_passcode`) VALUES (?,?)";
                conn = DriverManager.getConnection("jdbc:mysql://localhost/webshare", "root", "");
                pst = conn.prepareStatement(query);
                pst.setString(1,nameField.getText());
                pst.setString(2,passwordField.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "You have now created group!");
                Chatroom1 chatroom = new Chatroom1(nameField.getText());
           
                }
            catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }
        }
    }
            
    private class JoinGroupHandler implements ActionListener
    {
        String input = "";
        @Override
        public void actionPerformed(ActionEvent e) {   
            int result = JOptionPane.showConfirmDialog(null, createJoingroupPanel,
                   "Please enter the name and passcode of the group you want to join",
                   JOptionPane.OK_CANCEL_OPTION);
           
           if (result == JOptionPane.OK_OPTION){
                try {
                String query = "SELECT * FROM groupchats where Group_name=? and Group_passcode=?";
//                conn = DriverManager.getConnection("jdbc:mysql://localhost/websharedatabase", "root", "");
                conn = DriverManager.getConnection("jdbc:mysql://localhost/webshare", "root", "");
                pst = conn.prepareStatement(query);
                pst.setString(1,nameField.getText());
                pst.setString(2,passwordField.getText());                
                ResultSet resultSet = pst.executeQuery();
                
                if(!Pattern.matches(".*\\w.*", usernameField.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please enter a username");
                }
                
                else if (resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Group name and passcode matched");
                    Chatroom1 chat = new Chatroom1(usernameField.getText());
                }
                else {
                    JOptionPane.showMessageDialog(null, "Group name and passcode did not match"); 
                }
                }
            catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }     
        }
    }
    

}
