package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadHandler implements Runnable{

    private volatile boolean exit = false;
    Socket s;
    BufferedReader inputFromServer;
    public ReadHandler(Socket socket) throws IOException {
        inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        s = socket;
    }

    public void stop() {
        exit = true;
    }

    @Override
    public void run() {
        while(true){
            try {
               String serverOutput = inputFromServer.readLine();
               if (serverOutput==null){
                   System.exit(0);
               }else{
                   System.out.println(serverOutput);
               }

            }catch (IOException e){
                System.out.println(e);
            }
        }
    }
}
