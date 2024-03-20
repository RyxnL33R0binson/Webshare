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
import java.util.regex.*;

/**
 *
 * @author Ryan1
 */
public class SignupMenu 
{
    //REMOVE PRIVATE
    private Connection conn = null;
    private PreparedStatement pst = null;
    
    private JFrame signupMenuFrame = new JFrame("Sign up menu");
    
    private JPanelGradient northPanel = new JPanelGradient();
    private JPanelGradient centerPanel = new JPanelGradient();
    private JPanelGradient southPanel = new JPanelGradient();
    
    private JTextField firstNameField = new JTextField();
    private JTextField lastNameField = new JTextField();
    private JTextField emailField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    //private JTextField confirmPasswordField = new JTextField();
    
    private JLabel titleLabel = new JLabel("<html><span style='color: white;'>Webshare</span></html>");
    private JLabel firstNameLabel = new JLabel();
    private JLabel lastNameLabel = new JLabel();
    private JLabel emailLabel = new JLabel();
    private JLabel passwordLabel = new JLabel();
    private JLabel confirmpasswordLabel = new JLabel();
    private JLabel signupLabel = new JLabel();
    private JButton alreadyUserLabel = new JButton();
    
    private JButton signupButton = new JButton();
    
    
    public SignupMenu()
    {
        signupMenuFrame.setLayout(new BorderLayout());
        signupMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signupMenuFrame.setPreferredSize(new Dimension (500,800));
        signupMenuFrame.setLocationRelativeTo(null);
        signupMenuFrame.setResizable(false);
        
        signupMenuFrame.add(northPanel, BorderLayout.NORTH);
        signupMenuFrame.add(centerPanel, BorderLayout.CENTER);
        signupMenuFrame.add(southPanel, BorderLayout.SOUTH);
        
        setLocationAndSize();
        signupmenuComponents();
        setFont();
        setActionListener();
        
        signupMenuFrame.pack();
        signupMenuFrame.setVisible(true);
    }
    
    private void setLocationAndSize()
    {
       signupLabel.setBounds(190,70,200,40);
       firstNameLabel.setBounds(50,150,100,30);
       firstNameField.setBounds(150,150,250,30);
       
       lastNameLabel.setBounds(50,220,100,30);       
       lastNameField.setBounds(150,220,250,30);
       
       emailLabel.setBounds(50,290,100,30);
       emailField.setBounds(150,290,250,30);

       passwordLabel.setBounds(50,360,100,30);
       passwordField.setBounds(150,360,250,30);
       
       alreadyUserLabel.setOpaque(false);
       alreadyUserLabel.setContentAreaFilled(false);
       alreadyUserLabel.setBorderPainted(false);
      
       signupButton.setBounds(200,440,100,30);
    }
    private void signupmenuComponents()
    {
        northPanel.add(titleLabel);
        centerPanel.setLayout(null);
        centerPanel.add(signupLabel);
        centerPanel.add(firstNameLabel);
        centerPanel.add(firstNameField);
        centerPanel.add(lastNameLabel);
        centerPanel.add(lastNameField);
        centerPanel.add(emailLabel);
        centerPanel.add(emailField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);
        centerPanel.add(signupButton); 
        southPanel.add(alreadyUserLabel);
    }
    
    private void setFont()
    {
        titleLabel.setFont(new Font("SansSerif Plain", Font.BOLD, 55));
        titleLabel.setText("Webshare");
        signupLabel.setFont(new Font("SansSerif Plain", Font.BOLD, 30));
        signupLabel.setText("Sign up");
        firstNameLabel.setText("First name: ");
        lastNameLabel.setText("Last name: ");
        emailLabel.setText("Email address");
        passwordLabel.setText("Enter password");
        confirmpasswordLabel.setText("Confirm your password: ");
        signupButton.setText("Sign up");
        alreadyUserLabel.setText("Already a user? Click here");
    }

    private void setActionListener()
    {
        signupButton.addActionListener(new SignupHandler());
        alreadyUserLabel.addActionListener(new AlreadyUserHandler());
    }
    
    private class SignupHandler implements ActionListener
    {
        String ss = "";
        @Override
        public void actionPerformed(ActionEvent e) {
            try {    
                if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || emailField.getText().isEmpty()
                        || passwordField.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields");
                }
                else if(!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@?!#£%&$%^&+=])(?=\\S+$).{6,20}$", passwordField.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Password did not match the requirements");         
                }
                else if (!Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", emailField.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Invalid email");  
                }
                else{
                    JOptionPane.showMessageDialog(null, "You have now signed up!");
                    String query = "INSERT INTO `users`('First_name', 'Last_name', 'Email_address', 'Password') VALUES (?,?,?,?)";
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/webshare", "root", "");
                    pst = conn.prepareStatement(query);
                    pst.setString(1,firstNameField.getText());
                    pst.setString(2,lastNameField.getText());
                    pst.setString(3,emailField.getText());
                    pst.setString(4,passwordField.getText());
                    pst.executeUpdate();
                    MainMenu1 mainMenu = new MainMenu1();
                }
            } 
            catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }
    }
    
    private class AlreadyUserHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginMenu login = new LoginMenu();
        }
    }
    
}
