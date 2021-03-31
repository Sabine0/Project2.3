package app.networking;

public class Processor {
     Connection connection;
    // tweede argumet is UI
    public Processor(Connection connection){
          this.connection = connection;
    }

    public void start(){
        // thread maken  voor readArray en die naar de ui toe sturen
    }

    public String getGamelsit(){
        connection.write("get gamelist");
        System.out.println(connection.readQueue);
        return null;
    }
}
