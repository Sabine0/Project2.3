package app.state;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * The State class is an interface which holds the methods each view class will implement
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public abstract class State {
    /**
     * Code to be executed upon entering the state
     */
     public void enter() {
         System.out.println("Entering state");
     }

    /**
     * Code to be executed upon exiting the state
     */
    public void exit(){
        System.out.println("exiting state");
    }

    /**
     * Classes that extend the State class should override this method
     * @return the view of the new objectView
     */
    public Parent getView(){
        System.out.println("there should be a view here");
        return new Pane();
    }
}
