
/**
 * puur bedoeld voor testen
 */
package networking;

public class Test {
    public static void main(String[] args) {
        Connection c = new Connection();
        c.connect("145.33.225.170", 7789);

        Processor p = new Processor(c);

        try{
            p.getGamelist();
            p.getPlayerList();
            p.login("Danial");
            p.subscribe("Reversi");
            p.getPlayerList();
          //  Thread.sleep(10000);
           // p.login("Danial");
           // p.getPlayerList();
        }catch (Exception e){
            System.out.println(e);
        }

    }
}

