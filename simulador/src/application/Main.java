package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("layout_simulador.fxml"));
	        Scene scene = new Scene(root);
	        stage.setTitle("Jogo da velha: Por Carlos Melo");
	        stage.setScene(scene);
	        stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("application.css"));
	        Scene scene = new Scene(root);
	        stage.setTitle("Jogo da velha: Por Carlos Melo");
	        stage.setScene(scene);
	        stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public static void main(String[] args) {
		launch(args);
	}
}
