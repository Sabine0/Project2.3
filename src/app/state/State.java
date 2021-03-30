package app.state;

import javafx.scene.Parent;

public interface State {
    void enter();
    void exit();
    Parent getView();
}
