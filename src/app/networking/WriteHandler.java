package app.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class WriteHandler implements Runnable{

    private volatile boolean exit = false;
    private PrintWriter toServer;
    private List<String> writeQueue;
    BufferedReader userInput;

    //scanner is bedoeld voor test doeleiden
    Scanner scanner = new Scanner(System.in);

    public WriteHandler(Socket socket, List writeQueue) throws IOException {
        toServer = new PrintWriter(socket.getOutputStream(), true);
        this.writeQueue = writeQueue;
        //de userInput moet nog
    }
    public void stop() {
        exit = true;
    }

    @Override
    public void run() {
        while (!exit){
            synchronized (writeQueue){
                while (!writeQueue.isEmpty()){
                    toServer.println(writeQueue.remove(0));
                }
            }
            //toServer.println(scanner.nextLine());

        }
    }
}
