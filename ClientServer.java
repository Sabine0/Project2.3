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

            Thread tRead = new Thread(new ReadHandler(s));
            tRead.start();
     
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
