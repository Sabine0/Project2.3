package networking;

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
    List<String> readQueue = Collections.synchronizedList(new ArrayList<String>());
    // write arraylist
    List<String> writeQueue = Collections.synchronizedList(new ArrayList<String>());

    public Connection(String host, int port) {
          connect(host, port);
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

    public String read() {
        synchronized (readQueue) {
            return readQueue.remove(0);
        }
    }

    public void write(String commandToServer) {
        synchronized (writeQueue) {
            writeQueue.add(commandToServer);
        }
    }
}
