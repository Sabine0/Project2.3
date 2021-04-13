package app.view;

import javafx.scene.Parent;

/**
 *  The View interface ensures every view has a createView method
 * @author Sabine Schreuder
 * @version 09-04-21
 */
public interface View {
    /**
     * @return A parent containing the view for the respective state
     */
    public abstract Parent createView();
}
