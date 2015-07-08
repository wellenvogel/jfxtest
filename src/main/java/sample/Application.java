package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created by demskm81 on 07.07.2015.
 */
public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL r=getClass().getClassLoader().getResource("sample.fxml");
        System.out.println("Resource url="+r);
        Parent root = FXMLLoader.load(r);
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();


    }
    public void dolaunch(){
        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
        launch(Application.class,null);
    }
}
