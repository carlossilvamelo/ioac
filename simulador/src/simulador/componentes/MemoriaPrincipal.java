package simulador.componentes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class MemoriaPrincipal implements FuncoesMemorias{

	private Integer memoria[]= new Integer[64];//Mem�ria de 64 posi��es

	public MemoriaPrincipal() {//construtor
		limparMemoria();
	}

	/**
	 * Este m�todo pega o arquivo de entrada e carrega os dados na
	 * mem�ria Ram. O endere�o absoluto do arquivo vai ser dado como entrada.
	 *  
	 *  @param caminhoArquvo - String com o caminho absoluto do arquivo que
	 *  ser� lido.
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
				this.memoria[posicao++] = ler.nextInt(); //nome
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




	/**
	 * Verifica��o da existencia de um dado na memoria
	 * 
	 * @return  Retorna true se existe um dado na posi��o, false se n�o existe.
	 * */
	@Override
	public boolean verificarPosicaoDeMemoria(Integer endereco) {
		if(this.memoria[endereco]!=-1)
			return true;
		else
			return false;

	}	

	@Override
	public Integer getValorDe(Integer endereco) {
		return this.memoria[endereco];
	}
	@Override
	public void setValorDe(Integer endereco,Integer valor){
		this.memoria[endereco] = valor;
	}

	/**
	 * Limpa toda a mem�ria, atribuindo -1 a todas as posi��es.
	 * 
	 * */
	@Override
	public void limparMemoria() {
		for (int i = 0; i < memoria.length; i++) {
			memoria[i]=-1;
		}
	}

	//N�O PRECISA SER IMPLEMENTADO PARA MEMORIA PRINCIPAL
	@Override
	public Integer mapeamentoDireto(Integer enderecoRam) {
		// TODO Auto-generated method stub
		return null;
	}




}
