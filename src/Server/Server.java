package Server;
import java.net.*;
import java.io.*;
import java.util.*;
    
public class Server
{
    static ArrayList<ClientDatabase> clientList =new ArrayList<>();
    
    public static void main(String args[]) throws IOException
    {
        ServerSocket serverSocket =new ServerSocket(5432);
        
        while(true)
        {
            Socket soc = serverSocket.accept();
            DataInputStream din=new DataInputStream(soc.getInputStream());
            DataOutputStream dout=new DataOutputStream(soc.getOutputStream());  
                
            String dataIn =(String)din.readUTF();
            ClientDatabase clientInfo = new ClientDatabase(soc,din,dout,dataIn);
            Thread database = new Thread(clientInfo);
            clientList.add(clientInfo);
            database.start();
        }
    }
}

class ClientDatabase extends Server implements Runnable 
{
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private String name;
    private String clientName;
    private String temp;
        
    ClientDatabase(Socket soc,DataInputStream din, DataOutputStream dout,String str)
    {
        socket = soc;
        input = din;
        output = dout;
        name = str;
    }
    
    public void run()
    {
        while(true)
        {
            try
            {      
            clientName=input.readUTF();
            System.out.println(clientName);  
            
            for(ClientDatabase temp :clientList)
            {
                temp.output.writeUTF(clientName);
            }
            temp="************** "+name+" left **************";
            if(clientName.equals(temp))
            {
                clientList.remove(this);
                this.socket.close();
                break;
            }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            this.input.close();
            this.output.close();
        }
	catch(IOException e)
	{
            e.printStackTrace();
        }
    }
}