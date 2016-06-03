package simulador.componentes;

/**
 * Esta classe representa o componente "cacheL2" e implementa os metodos da
 * interface FuncoesMemorias de acordo com o a sua capacidade (32 posições de memória).
 */
public class CacheL2 implements FuncoesMemorias{

	private int memoria[]= new int[32];
	private int tag[]= new int[32];
	public CacheL2() {
		limparMemoria();
	}
	
	/**
	 * 
	 * @param endereco
	 * @return
	 */
	public boolean verificarTag(int endereco){
		
		if(tag[mapeamentoDireto(endereco)] == endereco)
			return true;


		return false;
	}
	/**
	 * 
	 * @param endereco
	 */
	public void atualizaTag(int endereco){
		tag[mapeamentoDireto(endereco)] = endereco;
	}
	public void printTags(){
		for (int i = 0; i < tag.length; i++) {
			System.out.print(tag[i]+" ");
		}
	}
	
	@Override
	public boolean verificarPosicaoDeMemoria(Integer endereco) {
		if(this.memoria[mapeamentoDireto(endereco)]!=-1)
			return true;
		else
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
	public boolean verificarPosicaoDeMemoria(Integer endereco,int valor) {
		
		if(this.memoria[mapeamentoDireto(endereco)] == valor){
			//hit
			return true;
		}
		return false;

	}

	@Override
	public void setValorDe(Integer endereco, Integer valor) {
		this.memoria[mapeamentoDireto(endereco)]=valor;
	}

	@Override
	public void limparMemoria() {
		for (int i = 0; i < memoria.length; i++) {
			memoria[i]=-1;
			tag[i]=-1;
		}
		
	}

	/**
	 * Este metodo recebe o endereço da memoria principal e faz um mapeamento direto para a cacheL2.
	 *
	 * @param enderecoRam the endereco ram
	 * @return the integer
	 */
	@Override
	public int mapeamentoDireto(int enderecoRam) {
		return enderecoRam%32;
	}

	@Override
	public Integer mapeamentoDireto(Integer enderecoRam) {
		return enderecoRam%32;
	}

}
