package ChatroomPackage;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;

class Chatroom1 implements WindowListener
{
    private JFrame frame;

    private JLabel heading;
    
    private Socket socket;
    private DataInputStream din;
    private DataOutputStream dout;
    private String username;
    private Thread th1,th2;
    
    private JButton sendButton;
    private JButton uploadButton;
    
    private JFileChooser openFileChooser;
    private BufferedImage originalBI;
    
    
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextArea chatArea;
    private JTextArea messageBox;
    
    private JPanelGradient backgroundPanel;
    //private backgroundJPanel.setBackground(new java.awt.Color(60, 78, 96));
    
    public Chatroom1 (String user)
    {
        frame = new JFrame("Chat room");
        sendButton = new JButton();
        jScrollPane1 = new JScrollPane();
        jScrollPane2 = new JScrollPane();
        heading = new JLabel();
        chatArea = new JTextArea();
        messageBox = new JTextArea();
        backgroundPanel = new JPanelGradient();
        username = user;
        uploadButton = new JButton();
        openFileChooser = new JFileChooser();
        openFileChooser.setCurrentDirectory(new File("c:\\temp"));
        openFileChooser.setFileFilter(new FileNameExtensionFilter("PNG images", "png", "jpeg"));
        
        
        setupComponents();
        setupLayout();
            try
                {
                    socket=new Socket("localhost",3912);
                    //This gets the name of the socket
                    din=new DataInputStream(socket.getInputStream());
                    //This outputs the name of the socket
                    dout=new DataOutputStream(socket.getOutputStream());
            
                    //This runs the WindowClosing method
                    frame.addWindowListener(this);

                    username = user;
                    String welcomeMessage="************** "+username+" has joined **************";
                    dout.writeUTF(username);
                    dout.writeUTF(welcomeMessage);

                    SendMessage r=new SendMessage(socket,din,chatArea,username,dout, openFileChooser);
                    th1=new Thread(r);
                    th1.start();
                       
                }
            catch(Exception e)
                {
                    e.printStackTrace();
                }      
    }

    private void setupComponents()
    {     
        chatArea.setColumns(20);
        chatArea.setRows(5);
        chatArea.setFont(new java.awt.Font("Calibri Light", 0, 20)); // NOI18N
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(chatArea);

        messageBox.setColumns(20);
        messageBox.setRows(5);
        messageBox.setFont(new java.awt.Font("Calibri Light", 0, 20)); 
        messageBox.setLineWrap(true);
        messageBox.setWrapStyleWord(true);
        jScrollPane2.setViewportView(messageBox);

        backgroundPanel.setBackground(new java.awt.Color(60, 78, 96));
        sendButton.setFont(new Font("Times New Roman", 1, 18));
        sendButton.setText("Send");
        uploadButton.setFont(new Font("Times New Roman", 1, 18));
        uploadButton.setText("Upload file");
        
        
        heading.setFont(new Font("Times New Roman", 1, 18));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setText("All messages in the group");
        sendButton.addActionListener(new SendButtonHandler());
        uploadButton.addActionListener(new UploadButtonHandler());
    }
    
    private void setupLayout()
    {
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(heading)
                .addGap(27, 27, 27)
                .addComponent(uploadButton)
                .addGap(47, 47, 47))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(heading, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uploadButton))
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frame.setMinimumSize(new Dimension(650, 650));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }// </editor-fold>
    
    public void windowClosing(WindowEvent e)
    {
        try
        {
            String str="************** "+username+" has left **************";
            dout.writeUTF(str);
        }
        catch(IOException f)
        {
            f.printStackTrace();
        }
    }
     

    public void windowOpened(WindowEvent e){}
    public void windowClosed(WindowEvent e){}
    public void windowIconified(WindowEvent e){}
    public void windowDeiconified(WindowEvent e){}
    public void windowActivated(WindowEvent e){}
    public void windowDeactivated(WindowEvent e){}
        
    
    //change back to write
    private class SendButtonHandler implements Runnable,ActionListener 
    { 
        String ss = "";     
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try 
            {
                dout.writeUTF(username +": "+ messageBox.getText());
                messageBox.setText("");
                messageBox.requestFocusInWindow();
            } 
            catch (IOException ex) 
            {
                ex.printStackTrace();
            }
        }
        @Override
        public void run()
        {
            sendButton.addActionListener(this);
            while(true)
            {
                ss=messageBox.getText();
            }
        }
    }
    
    private class UploadButtonHandler implements ActionListener
    {
        int returnValue;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            returnValue = openFileChooser.showOpenDialog(openFileChooser);
            if (returnValue == JFileChooser.APPROVE_OPTION) 
            {
                try {
                    originalBI = ImageIO.read(openFileChooser.getSelectedFile());
                    System.out.println("Uploaded successfully");
                } catch (IOException IO) {
                    IO.printStackTrace();
                    System.out.println("File did not upload");
                }
            }
            else {
                System.out.println("No file chosen");
            }
      
        }  
    }
}
class SendMessage extends JFrame implements Runnable
{
    Socket s;
    DataInputStream din;
    JTextArea t1;
    String temp=new String();
    String in=new String();
    String str=new String();
    DataOutputStream dout;
    JFileChooser doutPic = new JFileChooser();
        
    SendMessage(Socket x,DataInputStream y,JTextArea z,String tem,DataOutputStream h, JFileChooser pic)
    {
	s=x;
	din=y;
        t1=z;
        temp=tem;
        dout=h;
        doutPic = pic;
    }
        
    public void run()
    {
        while(true)
        {
            try
            {
                in=din.readUTF();
                if(in.equals(str))
                {
                    s.close();
                    break;
                }
                t1.append(in+"\n");

            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            din.close();
            dout.close();
        }
        catch(IOException f)
        {
            f.printStackTrace();
        }
    }
    
    public static void main(String[]args){
        new Chatroom1("User One");
    }

}

