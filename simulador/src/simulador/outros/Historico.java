package simulador.outros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Historico {

	
	public void teste(){
		File arq = new File("https://github.com/carlossilvamelo/ioac/blob/projeto/README.md");
		if(arq.exists())
			System.out.println("conectado");
		else
			System.out.println("falhou");
	}
	public void atualizar(){
		//http://carlosmeloti.com/arquivos/historico.txt
		FileReader reader = null;
		Scanner ler;
		try {

			//File arq = new File("http://carlosmeloti.com/arquivos/historico.txt");
			File arq = new File(File.separator+File.separator+"185.28.21.207"+File.separator+"c$"+File.separator+"/arquivos/historico.txt");
			reader = new FileReader(arq);
			ler = new Scanner(reader);

			
			while (ler.hasNext()) {
				
				System.out.println(ler.next());
				
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
