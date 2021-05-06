/**
 * This class is the core of the network package. It initializes the the and write threads, it manages the read, write
 * and notification queues.
 * @author Danial.B
 */

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.*;

public class Connection {
    // readQueue
    private List<String> readQueue;
    // write arraylist
    private List<String> writeQueue;
    //notification queue for massage unpredictable messages
    private List<String> notificationQueue;
    //Network logger for logging all the events across the network
    private Logger ntLogger;

    public Connection() {
               readQueue = Collections.synchronizedList(new ArrayList<String>());
               writeQueue = Collections.synchronizedList(new ArrayList<String>());
               notificationQueue = Collections.synchronizedList(new ArrayList<String>());
               ntLogger = Logger.getLogger("NetworkLogger");
               initializeNetworkLogger();
               ntLogger.log(Level.INFO, " - connection object created. Logger networkLogger initialized. ");

    }

    public boolean connect(String host, int port) {
        try {
            Socket s = new Socket(host, port);

            Thread tRead = new Thread(new ReadHandler(s, readQueue, notificationQueue));
            tRead.start();
            Thread tWtrite = new Thread(new WriteHandler(s, writeQueue));
            tWtrite.start();

            ntLogger.log(Level.INFO," - connected to ip address: " + host + "prot number: " + port );
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * this methode is used when the is a single line answer is expected form the server.
     * @return The answer of the server or the server error massage.
     * @throws ServerNotRespondingException If there is no response from the server .
     * @throws CommandFailedException  If server returns an error.
     */
    public String readSingleLine() throws ServerNotRespondingException, CommandFailedException {
        synchronized (readQueue) {
            try {
                while (readQueue.isEmpty()) {
                    readQueue.wait(2000);
                    if (!readQueue.isEmpty()) {
                        String response = readQueue.remove(0);
                        checkRespose(response);
                        ntLogger.log(Level.INFO,  " - Read queue size after read"+ readQueue.size());
                        System.out.println(" - rq size after read:"+readQueue.size());
                        return response;
                    }else{
                        throw new ServerNotRespondingException("There is no response form the the serve");
                    }
                }
            }catch (IllegalArgumentException | InterruptedException e){
                ntLogger.log(Level.INFO, " - problem while reading"+ e );
                System.out.println("problem while reading"+ e);
                return  "exception occurred";
            }
        }
        return " read methode server is not responding";
    }

    public String readDubbleLine() throws ServerNotRespondingException, CommandFailedException {
        synchronized (readQueue) {
            try {
                while (readQueue.isEmpty()) {
                    readQueue.wait(2000);
                    if (!readQueue.isEmpty()) {
                        checkRespose(readQueue.remove(0));
                        ntLogger.log(Level.INFO, " - Read queue size after the first read" + readQueue.size() );
                        System.out.println("Read queue size after the first read" + readQueue.size());
                        if(!readQueue.isEmpty()) {
                            ntLogger.log(Level.INFO,  " - second read is executed. rq size after the second read: "+ + readQueue.size());
                            System.out.println("is executed");
                            return readQueue.remove(0);
                        }else {

                        }
                    }else{
                        throw new ServerNotRespondingException("There is no response form the server");
                    }
                }
            }catch (IllegalArgumentException | InterruptedException e){
                ntLogger.log(Level.INFO, " - problem while reading"+ e );
                System.out.println("problem while reading"+ e);
                return  "exception occurd";
            }
        }
        return " read methode server is not responding";
    }

    /**
     * @return true if there is any new input from the server.
     */
    public boolean isNotification(){
        synchronized (notificationQueue){
            if(notificationQueue.isEmpty()){
                return false;
            }else{
                return true;
            }
        }
    }

    /**
     * @return the input from the server and delete it from the notificationQueue.
     */
    public String readNotification(){
        synchronized (notificationQueue){

            return  notificationQueue.remove(0);
        }
    }

    public void write(String commandToServer) {
        synchronized (writeQueue) {
            writeQueue.add(commandToServer);
        }
    }

    public List<String> getWriteQueue() {
        return writeQueue;
    }

    public List<String> getReadQueue() {
        return readQueue;
    }

    public boolean isReadQueueEmpty() {
        synchronized (readQueue) {
            if (readQueue.isEmpty()){
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * this methode is meant to check the first response of a server to a command. if its ok it will do nothing
     *  if there is and error it will create and exception with the error text and throw it.
     * @param
     * @throws CommandFailedException
     */
    private void checkRespose(String s) throws CommandFailedException {
        if (s.equals("OK")){
         synchronized (readQueue){
             try {
                 readQueue.wait(2000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
        }else if(s.startsWith("ERR")){
           // String[] arr = s.split(" ", 2);
            throw new CommandFailedException(s);
        }else{
            throw new CommandFailedException(s);
        }
    }

    private void initializeNetworkLogger(){

        try {
            System.setProperty("java.util.logging.SimpleFormatter.format","%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
            FileHandler fh = new FileHandler("NetworkLogs/networkLogs.log", true);
            ntLogger.addHandler(fh);
            ntLogger.setUseParentHandlers(false);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}