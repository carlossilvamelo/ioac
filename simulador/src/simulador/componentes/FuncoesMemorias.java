package simulador.componentes;


/**
 * Esta interface contém todos os métodos comuns que serão implementados
 * por cada memória.
 * */
public interface FuncoesMemorias {


	public boolean verificarPosicaoDeMemoria(Integer endereco);
	public Integer getValorDe(Integer endereco);
	public void setValorDe(Integer endereco,Integer valor);
	public void limparMemoria();
	public Integer mapeamentoDireto(Integer enderecoRam);
}
