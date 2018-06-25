package simulador.historico;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.Alert;
/**
 * Classe implementa a geração de um arquivo de log, baseado nos dados dos acessos do simulador,
 * este log é gerado em forma de arquivo texto.
 *
 */
public class ArquivoHistorico {

	/**
	 * Método cria e escreve no arquivo log
	 * @param lista Lista de Strings que representam cada linha da tabela do banco de dados, ou seja,
	 * representa cada acesso.
	 */
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
