/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatroomPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Ryan1
 */
public class WebshareTest
{
    //Fields
 
    public WebshareTest()
    {
        
    }

    public String validEmailForSignup(String temp)
    {
        String ss = "";
                if (!Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", temp))
                {
                    return ss = ss + "Invalid email for signup";  
                }
                else {    
                    return ss = ss + "Valid email for signup";
                }
    } 
    
    public String emptyFieldForSignupForm(String temp)
    {
        String ss = "";
        if (temp.isEmpty() || temp.isEmpty() || temp.isEmpty()
                        || temp.isEmpty())
        {
            return ss = ss + "Please fill in all signup fields";
        }
        else {
            return ss = ss + "All signup fields have field";
        }
    }
    
    public String validPasswordForSignup(String temp)
    {
        String ss = "";
        if(!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@?!#£%&$%^&+=])(?=\\S+$).{6,20}$", temp))
            {
                return ss = "Password does not match the requirements for signup";         
            }
        else {
            return ss = "Password is valid for signup";
        } 
    }          
   
    public String validUsernameForGroup(String temp)
    {
        String ss = "";
        if(!Pattern.matches(".*\\w.*", temp))
        {
            return ss = "Please enter a a";
        }
        else{
            return ss = "Valid username for group"; 
        }
    }

       
    public String validUserNameForJoinGroup(String temp)
    {
        String ss = "";
        if(!Pattern.matches(".*\\w.*", temp))
                {
                    return ss = ss + "Please enter a username";
                }
        else {
            return ss = ss + "Valid username to enter group";
        }
    }
}
