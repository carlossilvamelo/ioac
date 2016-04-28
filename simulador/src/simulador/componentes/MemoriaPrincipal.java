package simulador.componentes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class MemoriaPrincipal implements FuncoesMemorias{

	public Integer memoriaRam[]= new Integer[64];

	public MemoriaPrincipal() {
		limparMemoria();
	}

	/**
	 * Este método pega o arquivo de entrada e carrega os dados na
	 *  memória Ram. O endereço absoluto do arquivo vai ser dado como entrada.
	 *  
	 *  @param caminhoArquvo - String com o caminho absoluto do arquivo que
	 *  será lido.
	 *  
	 *  @author Carlos Melo
	 * */
	public void carregarMemoriaRam(String caminhoArquvo){
		FileReader reader = null;
		Scanner ler;
		int posicao=0;
		try {

			File arq = new File(caminhoArquvo);
			reader = new FileReader(arq);
			ler = new Scanner(reader);

			//lendo arquivo e atribuindo a memoria
			while (ler.hasNext()) {
				this.memoriaRam[posicao++] = ler.nextInt(); //nome
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

	}//carregarMemoriaRam

	@Override
	public void atualizarValor(Integer index) {
		
	}

	
	@Override
	public void verificarPsicaoPorEndereco(Integer endereco) {
		// TODO Auto-generated method stub

	}	

	@Override
	public void getValorDe(Integer endereco) {
		// TODO Auto-generated method stub

	}


	/**
	 * Limpa toda a memoria, atribuindo -1 a todas as posições.
	 * 
	 * */
	@Override
	public void limparMemoria() {
		for (int i = 0; i < memoriaRam.length; i++) {
			memoriaRam[i]=-1;
		}
	}




}
