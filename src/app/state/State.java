package app.state;

import app.networking.serverNotRespondingException;
import javafx.scene.Parent;

public interface State {
    void enter() throws serverNotRespondingException;
    void exit();
    Parent getView();
}
