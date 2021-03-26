package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientServer {

    public static void main(String[] args) {
        try{
            Socket s = new Socket("localhost", 7789);
            DataInputStream din=new DataInputStream(s.getInputStream());
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());

            // string to read message from input
            String line = "";

            // keep reading until "Over" is input
            while (!line.equals("Over"))
            {
                try
                {
                    line = din.readLine();
                    System.out.println(line);
                    dout.writeUTF(line);
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
