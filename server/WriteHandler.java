package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteHandler implements Runnable{

    PrintWriter toServer;
    BufferedReader userInput;
    //scanner is bedoeld voor test doeleiden
    Scanner scanner = new Scanner(System.in);
    public WriteHandler(Socket socket) throws IOException {
        toServer = new PrintWriter(socket.getOutputStream(), true);
        //de userInput moet nog
    }

    @Override
    public void run() {
        while (true){
            toServer.println(scanner.nextLine());
        }
        
    }
}
