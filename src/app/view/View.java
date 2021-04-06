
package app.view;

import javafx.scene.Parent;

/**
 * The View class is an interface which holds the methods each view class will implement
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public interface View {
    /**
     * @return The view
     */
    public Parent buildSceneGraph();
}
