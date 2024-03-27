/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatroomPackage;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import static java.awt.Color.*;


/**
 *
 * @author Ryan1
 */
public class LoginMenu 
{
 
    private Connection conn = null;
    private PreparedStatement pst = null;
    
    private JFrame loginMenuFrame = new JFrame("Login menu");
    
    private JLabel titleLabel = new JLabel("<html><span style='color: white;'>Webshare</span></html>");
    
    private JPanelGradient northPanel = new JPanelGradient();
    private JPanelGradient centerPanel = new JPanelGradient();
    private JPanelGradient southPanel = new JPanelGradient();
    
    private JLabel signinLabel = new JLabel();
    private JLabel emailLabel = new JLabel();
    private JLabel passwordLabel = new JLabel();
    
    private JTextField emailField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton signinButton = new JButton();
    private JButton notaUserButton = new JButton();
    
    
    public LoginMenu()
    {
        loginMenuFrame.setLayout(new BorderLayout());
        loginMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginMenuFrame.setPreferredSize(new Dimension (500,800));
        loginMenuFrame.setResizable(false);
        

        loginMenuFrame.add(northPanel, BorderLayout.NORTH);
        loginMenuFrame.add(centerPanel, BorderLayout.CENTER);
        loginMenuFrame.add(southPanel, BorderLayout.SOUTH);
        
        setLocationAndSize();
        loginMenuComponents();
        setFont();
        setActionListener();

       
        loginMenuFrame.pack();
        loginMenuFrame.setLocationRelativeTo(null);
        loginMenuFrame.setVisible(true);
    }
    
    private void loginMenuComponents()
    {
        northPanel.add(titleLabel);
        centerPanel.setLayout(null);
        centerPanel.add(signinLabel);
        centerPanel.add(emailLabel);
        centerPanel.add(emailField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);
        centerPanel.add(signinButton); 
        southPanel.add(notaUserButton);
    }
    
    private void setLocationAndSize()
    {
       signinLabel.setBounds(190,70,100,40);
       
       emailLabel.setBounds(50,150,100,30);
       emailField.setBounds(150,150,250,30);
       
       passwordLabel.setBounds(50,220,100,30);       
       passwordField.setBounds(150,220,250,30);
 
       signinButton.setBounds(190,350,100,30);
       
       notaUserButton.setOpaque(false);
       notaUserButton.setContentAreaFilled(false);
       notaUserButton.setBorderPainted(false);
    }
    
    private void setFont()
    {
        titleLabel.setFont(new Font("SansSerif Plain", Font.BOLD, 55));
        titleLabel.setText("Webshare");
        signinLabel.setFont(new Font("SansSerif Plain", Font.BOLD, 30));
        signinLabel.setText("Sign in");
        emailLabel.setText("Email address");
        passwordLabel.setText("Password");
        signinButton.setText("Login");
        notaUserButton.setText("Not a user? Click here");
    }

    private void setActionListener()
    {
        signinButton.addActionListener(new SigninHandler());
        notaUserButton.addActionListener(new SignupHandler());
    }
     
    private class SigninHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {   
                try {
                String query = "SELECT * FROM users where Email_address=? and Password=?";
                conn = DriverManager.getConnection("jdbc:mysql://localhost/webshare", "root", "");
                pst = conn.prepareStatement(query);
                pst.setString(1,emailField.getText());
                pst.setString(2,passwordField.getText());
                ResultSet resultSet = pst.executeQuery();
                
                if (resultSet.next()) {
                    MainMenu1 mainMenu = new MainMenu1();
                }
                else {
                    JOptionPane.showMessageDialog(null, "User not found. Please try again"); 
                }
                conn.close();
                }
            catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }     
    }
    
    private class SignupHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            SignupMenu login = new SignupMenu();
        }
    }

}

