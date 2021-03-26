package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadHandler implements Runnable{

    BufferedReader inputFromServer;
    public ReadHandler(Socket socket) throws IOException {
        inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }


    @Override
    public void run() {
        while(inputFromServer != null){
            try {
                System.out.println(inputFromServer.readLine());
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }
}
