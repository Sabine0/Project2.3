package app.state;

import app.networking.ServerNotRespondingException;
import javafx.scene.Parent;

public interface State {
    void enter() throws ServerNotRespondingException;
    void exit();
    Parent getView();
}
