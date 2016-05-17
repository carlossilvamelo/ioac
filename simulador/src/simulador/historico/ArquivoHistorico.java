package simulador.historico;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.Alert;

public class ArquivoHistorico {

	public static void escritor(ArrayList<String> lista){
		BufferedWriter buffWrite;
		try {
			buffWrite = new BufferedWriter(new FileWriter("/log_simulador.txt"));
			for (int i = 0; i < lista.size(); i++) {
				buffWrite.write(lista.get(i));
				buffWrite.newLine();
				
			}
			
			buffWrite.close();
			
			
		} catch (IOException e) {
			 Alert dialogoAviso = new Alert(Alert.AlertType.ERROR);
	         dialogoAviso.setTitle("Arquivo log");
	         dialogoAviso.setHeaderText("");
	         dialogoAviso.setContentText("Não foi possível gerar o arquivo de log!");
	         dialogoAviso.showAndWait();
		}
		
	}
}
