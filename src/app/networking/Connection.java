package app.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
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

    public String read() throws serverNotRespondingException {
        synchronized (readQueue) {
            try {
                while (readQueue.isEmpty()) {
                    readQueue.wait(2000);
                    if (!readQueue.isEmpty()) {
                        return readQueue.remove(0);
                    }else{
                        throw new serverNotRespondingException("There is no response form the the serve");
                    }
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return "server is not responding";
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

}
