package simulador.componentes;

public class CacheL2 implements FuncoesMemorias{

	private int memoria[]= new int[32];
	
	public CacheL2() {
		limparMemoria();
	}
	
	@Override
	public boolean verificarPosicaoDeMemoria(Integer endereco) {
		if(this.memoria[mapeamentoDireto(endereco)]!=-1)
			return true;
		else
			return false;

	}
	public int[] getMemoria(){
		return memoria;
	}

	@Override
	public Integer getValorDe(Integer endereco) {
		return this.memoria[mapeamentoDireto(endereco)];
	}
	
	@Override
	public boolean verificarPosicaoDeMemoria(Integer endereco,int valor) {
		System.out.println(this.memoria[mapeamentoDireto(endereco)] +"  " + valor);
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
		}
		
	}

	/**
	 * Este metodo recebe o endereço da memoria principal e faz um mapeamento direto para a cacheL2
	 * */
	@Override
	public Integer mapeamentoDireto(Integer enderecoRam) {
		return enderecoRam%32;
	}

}
