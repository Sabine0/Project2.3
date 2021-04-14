/**
 * This class is responsible for getting the input from the server and put it in the right queue.
 * @author Danial.B
 */
package app.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadHandler implements Runnable {

    private volatile boolean exit = false;
    private static final String[] notificationIdentifier = {"SVR GAME CHALLENGE {", "SVR GAME CHALLENGE CANCELLED {", "SVR GAME YOURTURN {", "SVR GAME MATCH {", "SVR GAME MOVE {", "SVR GAME WIN {", "SVR GAME LOSE {", "SVR GAME DRAW {", "SVR GAME LOSS {"};
    private BufferedReader inputFromServer;
    private List<String> readQueue;
    private List<String> notificationQueue;
    private Logger ntLogger = Logger.getLogger("NetworkLogger");

    public ReadHandler(Socket socket, List readQueue, List notificationQueue) throws IOException {
        inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.readQueue = readQueue;
        this.notificationQueue = notificationQueue;

    }

    public void stop() {
        exit = true;
    }

    private boolean isNotification(String s) {
        for (String var : notificationIdentifier) {
            if (s.startsWith(var)) {
                //System.out.println("match ntf" + s + "to  ntfi " + var);
                return true;
            }
        }
        return false;
    }
        // return Arrays.stream(notificationIdentifier).anyMatch(s ::equals);



    @Override
    public void run() {
        while(true){
            try {
               String serverOutput = inputFromServer.readLine();
               if (serverOutput==null){
                   ntLogger.log(Level.INFO,  "server disconnected. (recieving null)");
                   System.exit(0);
               }else if(serverOutput.equals("Strategic Game Server Fixed [Version 1.1.0]") || serverOutput.equals("(C) Copyright 2015 Hanzehogeschool Groningen") || serverOutput.equals("New Game Server [Version 1.0]") || serverOutput.equals("(C) Copyright 2021 Hanzehogeschool Groningen")){

                }else if(isNotification(serverOutput)){
                   synchronized (notificationQueue){
                       notificationQueue.add(serverOutput);
                       System.out.println("added to ntq: " + serverOutput);
                       System.out.println("ntq size after adding ntf: " + notificationQueue.size());
                       ntLogger.log(Level.INFO, " - ntq size after adding ntf: " + notificationQueue.size());
                       ntLogger.log(Level.INFO, " - added to ntq: " + serverOutput );
                   }
               }else{
                   //System.out.println(serverOutput);
                   synchronized (readQueue){
                       readQueue.add(serverOutput);
                       ntLogger.log(Level.INFO, " - read queue size: "+ readQueue.size());
                       System.out.println("read queue size: "+ readQueue.size());
                       System.out.println("add to read queue: " + serverOutput);
                       ntLogger.log(Level.INFO, " - add to read queue: " + serverOutput );
                       readQueue.notifyAll();
                   }
               }
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }
}
