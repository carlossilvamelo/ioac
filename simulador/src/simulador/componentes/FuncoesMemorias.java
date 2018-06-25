package simulador.componentes;


/**
 * Esta interface cont�m todos os m�todos comuns que ser�o implementados
 * por cada mem�ria, caches e mem�ria principal.
 * */
public interface FuncoesMemorias {
	
	/**
	 * Verifica valor da tag em determinada posi��o de mem�ria com mapeamento direto.
	 * 
	 * @param endereco Endere�o da mem�ria RAM
	 */
	public boolean verificarTag(int endereco);

	/**
	 * Atualiza o vetor das tags da mem�rias caches
	 * 
	 * @param endereco endereco Endere�o da mem�ria RAM
	 */
	public void atualizaTag(int endereco);
	
	/**
	 * M�todo faz a verifica��o de uma determinada posi��o de mem�ria.
	 *
	 * @param endereco - endere�o da mem�ria principal
	 * @return - true se encontrou dados alocados na mem�ria
	 */
	public boolean verificarPosicaoDeMemoria(Integer endereco);
	/**
	 * M�todo para acessar o valor de uma posi��o de uma determinada mem�ria.
	 * 
	 * @param endereco - endere�o da mem�ria principal
	 * @return - valor de uma determinada posi��o da mem�ria
	 */
	public Integer getValorDe(Integer endereco);
	/**
	 * M�todo para setar o valor de uma posi��o de uma determinada mem�ria.
	 * 
	 * @param endereco - endere�o da mem�ria principal
	 * @param valor - novo valor
	 */
	public void setValorDe(Integer endereco,Integer valor);
	/**
	 * M�todo seta a mem�ria com -1 em todas as posi��es, o que significa que a posi��o est�
	 * vazia.
	 */
	public void limparMemoria();
	
	/**
	 * Mapeamento direto.
	 *
	 * @param enderecoRam the endereco ram
	 * @return - retorna o calculo do mapeamento direto em uma determinada mem�ria.
	 */
	public Integer mapeamentoDireto(Integer enderecoRam);
	
	/**
	 * M�todo faz a verifica��o de uma determinada posi��o de mem�ria.
	 *
	 * @param endereco - endere�o da mem�ria principal
	 * @param valor - valor para compara��o do dado em uma determinada posi��o damem�ria
	 * @return - true se encontrou um determinado dado alocado na mem�ria
	 */
	public boolean verificarPosicaoDeMemoria(Integer endereco, int valor);
	
	/**
	 * M�todo retorna o vetor de inteiros que representa a mem�ria.
	 *
	 * @return the memoria
	 */
	public int[] getMemoria();
	int mapeamentoDireto(int enderecoRam);
}
