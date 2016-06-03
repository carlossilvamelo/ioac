package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simulador.historico.ConexaoMySql;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * Essa classe apenas carrega e mostra na tela a janela da aplicação.
 */
public class Main extends Application {

	@Override
	public void start(Stage stage){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("layout_simulador.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("Simulador");
			stage.setScene(scene);
			stage.show();
			ConexaoMySql.conectar();


			//fechar o banco quando fechar a janela
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent arg0) {

					if(ConexaoMySql.isStatus())
						ConexaoMySql.fecharConexao();
					
					stage.close();
				}

			});


		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

}
