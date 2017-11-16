package teste.jpa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Eric-A- on 31/08/2017.
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(Main.class.getResource("vues/tableview.fxml"));

        primaryStage.setTitle("Table");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String...args)
    {
        Application.launch(args);
    }
}
