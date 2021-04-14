package app.state;

import app.view.LoginScreenView;
import javafx.scene.Parent;

public class LoginScreenState extends State{
    private String game;
    private boolean appUserHuman;

    public LoginScreenState(String game, boolean appUserHuman){
        this.appUserHuman = appUserHuman;
        this.game = game;
    }
    /**
     * Code to be executed upon entering the state
     */
    @Override
    public void enter(){
        System.out.println("Entering login screen");
    }

    /**
     * Code to be executed upon exiting the state
     */
    @Override
    public void exit(){
        System.out.println("exiting login screen");
    }


    @Override
    public Parent getView(){
        return new LoginScreenView(game, appUserHuman).createView();
    }
}
