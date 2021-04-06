package app.networking;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Connection {
    // readQueue
    private List<String> readQueue;
    // write arraylist
    private List<String> writeQueue;

    public Connection() {
               readQueue = Collections.synchronizedList(new ArrayList<String>());
               writeQueue = Collections.synchronizedList(new ArrayList<String>());
    }

    public boolean connect(String host, int port) {
        try {
            Socket s = new Socket(host, port);

            Thread tRead = new Thread(new ReadHandler(s, readQueue));
            tRead.start();
            Thread tWtrite = new Thread(new WriteHandler(s, writeQueue));
            tWtrite.start();


            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public String readSingleLine() throws serverNotRespondingException, CommandFailedException {
        synchronized (readQueue) {
            try {
                while (readQueue.isEmpty()) {
                    readQueue.wait(2000);
                    if (!readQueue.isEmpty()) {
                        String response = readQueue.remove(0);
                        checkRespose(response);
                        System.out.println("rq size after read:"+readQueue.size());
                        return response;
                    }else{
                        throw new serverNotRespondingException("There is no response form the the serve");
                    }
                }
            }catch (IllegalArgumentException | InterruptedException e){
                System.out.println("problem while reading"+ e);
                return  "exception occurd";
            }
        }
        return " read methode server is not responding";
    }

    public String readDubbleLine() throws serverNotRespondingException, CommandFailedException {
        synchronized (readQueue) {
            try {
                while (readQueue.isEmpty()) {
                    readQueue.wait(2000);
                    if (!readQueue.isEmpty()) {
                        checkRespose(readQueue.remove(0));
                        System.out.println(readQueue.size());
                        if(!readQueue.isEmpty()) {
                            System.out.println("is exceuted");
                            return readQueue.remove(0);
                        }else {

                        }
                    }else{
                        throw new serverNotRespondingException("There is no response form the the serve");
                    }
                }
            }catch (IllegalArgumentException | InterruptedException e){
                System.out.println("problem while reading"+ e);
                return  "exception occurd";
            }
        }
        return " read methode server is not responding";
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

}
