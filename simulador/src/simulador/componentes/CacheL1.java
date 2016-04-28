package simulador.componentes;

public class CacheL1 implements FuncoesMemorias{
	
	private Integer memoria[]= new Integer[8];
	
	public CacheL1() {
		limparMemoria();
		
	}
	
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
	public void setValorDe(Integer endereco, Integer valor) {
		this.memoria[endereco]=valor;
	}

	@Override
	public void limparMemoria() {
		for (int i = 0; i < memoria.length; i++) {
			memoria[i]=-1;
		}
		
	}

	/**
	 * Este metodo recebe o endereço da memoria principal e faz um mapeamento direto para a cacheL1
	 * 
	 * 
	 * @author Carlos Melo
	 * */
	@Override
	public Integer mapeamentoDireto(Integer enderecoRam) {
		return enderecoRam%8;
	}

}
