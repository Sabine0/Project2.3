package app.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

public class ReadHandler implements Runnable{

    private volatile boolean exit = false;
    private Socket s;
    private BufferedReader inputFromServer;
    private List<String> readQueue;

    public ReadHandler(Socket socket, List readQueue) throws IOException {
        inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.readQueue = readQueue;
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
               }else if(serverOutput.equals("Strategic Game Server Fixed [Version 1.1.0]") || serverOutput.equals("(C) Copyright 2015 Hanzehogeschool Groningen") ||serverOutput.equals("OK") ){

                }else{
                   //System.out.println(serverOutput);
                   synchronized (readQueue){
                       readQueue.add(serverOutput);
                       System.out.println("rq"+ readQueue.size());
                       readQueue.notifyAll();
                       System.out.println("add to read queue" + serverOutput);
                   }
               }
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }
}
