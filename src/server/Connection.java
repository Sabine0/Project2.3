package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {

        public boolean connect(String host, int port) {
            try {
                Socket s = new Socket(host, port);


                Thread tRead = new Thread(new ReadHandler(s));
                tRead.start();
                Thread tWtrite = new Thread(new WriteHandler(s));
                tWtrite.start();
                return true;

            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        }
}
