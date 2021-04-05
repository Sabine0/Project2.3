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

    public String getGamelsit() throws serverNotRespondingException{
        connection.write("get gamelist");
 /*       try {
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println(e);
        }*/
        System.out.println("proccesor" +connection.read());
        return null;
    }
}
