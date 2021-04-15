/**
 * This class is responsible for writing data to the server.
 * @author Danial.B
 */
package app.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteHandler implements Runnable {

    private volatile boolean exit = false;
    private PrintWriter toServer;
    private List<String> writeQueue;
    BufferedReader userInput;
    private Logger ntLogger = Logger.getLogger("NetworkLogger");

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
        while (true) {
            synchronized (writeQueue) {
                while (!writeQueue.isEmpty()) {
                    ntLogger.log(Level.INFO, " - Write queue size: " + writeQueue.size() );
                    System.out.println("wq size = " + writeQueue.size());
                    String tosend = writeQueue.remove(0);
                    ntLogger.log(Level.INFO, " - is writing: "+ tosend );
                    System.out.println("is writing: "+ tosend);
                    toServer.println(tosend);
                }
            }
        }
        //toServer.println(scanner.nextLine());

    }
}
