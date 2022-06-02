package code;

import code.utilities.Const;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import code.network.Network;

import java.io.IOException;

public class Main extends Application {
    Stage ps;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("The Game!");
        primaryStage.setScene(new Scene(root, 1366, 768));
        primaryStage.setFullScreen(true);
        primaryStage.show();

        ps = primaryStage;
    }

    @Override
    public void stop() {
        Network network = new Network();
        network.send(Const.EXIT);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
