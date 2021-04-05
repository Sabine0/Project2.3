package app.networking;

public class Test {
    public static void main(String[] args) {
        Connection c = new Connection();
        c.connect("localhost", 7789);

        Processor p = new Processor(c);
        try{
            p.getGamelsit();
            p.login("Danial");
          //  Thread.sleep(10000);
            //p.login("Danial");
           // p.getPlayerList();
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
