package simulador.componentes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Esta classe representa o componente "memória principal" e implementa os metodos da
 * interface FuncoesMemorias de acordo com o a sua capacidade (64 posições de memória).
 */
public class MemoriaPrincipal implements FuncoesMemorias{

	private int memoria[]= new int[64];

	public MemoriaPrincipal() {
		limparMemoria();
	}

	/**
	 * Este método pega o arquivo de entrada e carrega os dados na
	 * memória Ram. O endereço absoluto do arquivo vai ser dado como entrada.
	 *  
	 *  @param caminhoArquvo - String com o caminho absoluto do arquivo que
	 *  será lido.
	 *  
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
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Caminho invalido, não foi possível abrir o arquivo");
			
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
	
	@Override
	public int[] getMemoria(){
		return memoria;
	}

	@Override
	public boolean verificarPosicaoDeMemoria(Integer endereco, int valor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int mapeamentoDireto(int enderecoRam) {
		// TODO Auto-generated method stub
		return 0;
	}


}
