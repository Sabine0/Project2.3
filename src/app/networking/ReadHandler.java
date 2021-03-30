package networking;

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
               }else{
                   //System.out.println(serverOutput);
                   synchronized (readQueue){
                       readQueue.add(serverOutput);
                       System.out.println(serverOutput);
                   }
               }
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }
}
