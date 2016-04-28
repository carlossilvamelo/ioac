package simulador.componentes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class MemoriaPrincipal implements FuncoesMemorias{

	private Integer memoria[]= new Integer[64];//Memória de 64 posições

	public MemoriaPrincipal() {//construtor
		limparMemoria();
	}

	/**
	 * Este método pega o arquivo de entrada e carrega os dados na
	 * memória Ram. O endereço absoluto do arquivo vai ser dado como entrada.
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
	 * Verificação da existencia de um dado na memoria
	 * 
	 * @return  Retorna true se existe um dado na posição, false se não existe.
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
	 * Limpa toda a memória, atribuindo -1 a todas as posições.
	 * 
	 * */
	@Override
	public void limparMemoria() {
		for (int i = 0; i < memoria.length; i++) {
			memoria[i]=-1;
		}
	}

	//NÃO PRECISA SER IMPLEMENTADO PARA MEMORIA PRINCIPAL
	@Override
	public Integer mapeamentoDireto(Integer enderecoRam) {
		// TODO Auto-generated method stub
		return null;
	}




}
