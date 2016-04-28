package simulador.componentes;


/**
 * Esta interface cont�m todos os m�todos comuns que ser�o implementados
 * por cada mem�ria.
 * */
public interface FuncoesMemorias {


	public boolean verificarPosicaoDeMemoria(Integer endereco);
	public Integer getValorDe(Integer endereco);
	public void setValorDe(Integer endereco,Integer valor);
	public void limparMemoria();
	public Integer mapeamentoDireto(Integer enderecoRam);
}
