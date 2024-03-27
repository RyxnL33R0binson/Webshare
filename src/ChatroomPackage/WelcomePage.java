/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatroomPackage;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import static java.awt.Color.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.*;

/**
 *
 * @author Ryan1
 */
public class WelcomePage
{
    private JFrame welcomepageFrame = new JFrame("Welcome page");

    private JPanelGradient centerPanel = new JPanelGradient();
    private JPanelGradient northPanel = new JPanelGradient();
    private JPanelGradient southPanel = new JPanelGradient();

    private JLabel titleLabel = new JLabel("<html><span style='color: white;'>Webshare</span></html>");
    private JLabel headingOne = new JLabel();

    private JTextArea paragraphOne = new JTextArea();
    
    private JButton signupButton = new JButton();
    private JButton signinButton = new JButton();
    
    
    public WelcomePage() throws IOException
    { 
        welcomepageFrame.setLayout(new BorderLayout());
        welcomepageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomepageFrame.setPreferredSize(new Dimension (1000,800));
        welcomepageFrame.setResizable(false);

        welcomepageFrame.add(northPanel, BorderLayout.NORTH);
        welcomepageFrame.add(centerPanel, BorderLayout.CENTER);
        welcomepageFrame.add(southPanel, BorderLayout.SOUTH);
 
        setLocationAndSize();
        welcomePageComponents();
        setFont();
        setActionListener();
    
        welcomepageFrame.pack();
        welcomepageFrame.setLocationRelativeTo(null);
        welcomepageFrame.setVisible(true);

    }

    private void setLocationAndSize()
    {
        signinButton.setSize(new Dimension(100, 100));
        signupButton.setSize(new Dimension(40, 40));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); 
        southPanel.setLayout(new BoxLayout(southPanel,BoxLayout.X_AXIS));
    }
    
    private void welcomePageComponents()
    {
        northPanel.add(titleLabel);
        
        centerPanel.add(headingOne);
        centerPanel.add(paragraphOne);
        centerPanel.add(southPanel);
        
        southPanel.add(signinButton);
        southPanel.add(signupButton);
        southPanel.add(Box.createHorizontalGlue());

        paragraphOne.setEditable(false);
        //paragraphTwo.setEditable(false);
        paragraphOne.setOpaque(false);
        //paragraphTwo.setOpaque(false);
    }
    
    private void setFont()
    {
        titleLabel.setFont(new Font("SansSerif Plain", Font.BOLD, 55));

        paragraphOne.setText(getDescription());
        paragraphOne.setFont(new Font("SansSerif Plain", Font.PLAIN, 22));
        paragraphOne.setLineWrap(true);
        paragraphOne.setWrapStyleWord(true);
        
        signinButton.setText("Login");
        signupButton.setText("Sign-up");
    }
    
    
    private String getDescription()
    {
        String ss = "";
        ss = ss + "Webshare is an online program which allows mutiple clients to interact with each"
                + " other over a server.\n\n\n Why Webshare?\n Webshare is a free service and unlike most"
                + "social media apps it is simple to use and has straight  forward interface." 
                + "\n\nCOVID-19\n"
                + "There is no doubt COVID-19 pandemic has had a huge impact on everyone. "
                + "Most, if not all countries around the world, have been in lockdownm preventing family and friends from "
                + "communicating with each other, teachers having to teach remotely, business closing and the list goes on"
                +" \n\nWebshare allows users to communicate with anyone we want, over the web for free. The simple"
                + " interface makes it ideal for people of all ages to use understand and use family and friends\n"
                + "Another point which makes Webshare stand out from the rest is the private.\n"
                + "\nWebshare allows users to create a group with a name and passcode users must enter to join the group\n"
                + "this prevents intruders and random people from getting into the chatroom a possibly stealing important information"
                + "The platform is a great way collaborating with one another on anything\n"
                + "So sign up now and share how you are doing with the people your loved ones.";
        return ss;
    }

    private void setActionListener()
    {
        signupButton.addActionListener(new SignupHandler());
        signinButton.addActionListener(new SigninHandler());
    }
    
    
    private class SigninHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginMenu login = new LoginMenu();
          
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }        
    }

    
    private class SignupHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            SignupMenu signup = new SignupMenu();
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    public static void main(String []args) throws IOException
    {
        WelcomePage page = new WelcomePage();
    }
}
