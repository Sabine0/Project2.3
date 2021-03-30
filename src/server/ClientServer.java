package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientServer {

    public static void main(String[] args) {
        try{
            Socket s = new Socket("145.33.225.170", 7789);
            //DataInputStream din=new DataInputStream(s.getInputStream());
            //DataOutputStream dout=new DataOutputStream(s.getOutputStream());

            Thread tRead = new Thread(new ReadHandler(s));
            tRead.start();
            Thread tWtrite = new Thread(new WriteHandler(s));
            tWtrite.start();

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
