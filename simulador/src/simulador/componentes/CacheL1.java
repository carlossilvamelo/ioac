package simulador.componentes;

/**
 * Esta classe representa o componente "cacheL1" e implementa os metodos da
 * interface FuncoesMemorias de acordo com o a sua capacidade (8 posições de memória)
 */
public class CacheL1 implements FuncoesMemorias{

	private int memoria[]= new int[8];

	public CacheL1() {
		limparMemoria();
	}

	@Override
	public boolean verificarPosicaoDeMemoria(Integer endereco,int valor) {


		if(this.memoria[mapeamentoDireto(endereco)] == valor){
			//hit
			return true;
		}
		return false;

	}
	@Override
	public int[] getMemoria(){
		return memoria;
	}


	@Override
	public Integer getValorDe(Integer endereco) {
		return this.memoria[mapeamentoDireto(endereco)];
	}

	@Override
	public void setValorDe(Integer endereco, Integer valor) {
		this.memoria[mapeamentoDireto(endereco)]=valor;
	}

	@Override
	public void limparMemoria() {
		for (int i = 0; i < memoria.length; i++) {
			memoria[i]=-1;
		}

	}

	/**
	 * Este método recebe o endereço da memoria principal e faz um mapeamento direto para a cacheL1
	 * 
	 * 
	 * */
	@Override
	public Integer mapeamentoDireto(Integer enderecoRam) {
		return enderecoRam%8;
	}

	@Override
	public boolean verificarPosicaoDeMemoria(Integer endereco) {
		// TODO Auto-generated method stub
		return false;
	}

}
