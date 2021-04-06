package app.state;

import app.networking.ServerNotRespondingException;
import javafx.scene.Parent;

/**
 * The State class is an interface which holds the methods each view class will implement
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public interface State {
    /**
     * Code to be executed upon entering the login screen state
     */
    void enter() throws ServerNotRespondingException;

    /**
     * Code to be executed upon exiting the login screen state
     */
    void exit();

    /**
     * @return the view of the new objectView
     */
    Parent getView();
}
