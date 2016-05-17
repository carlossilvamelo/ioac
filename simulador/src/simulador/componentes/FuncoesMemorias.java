package simulador.componentes;


/**
 * Esta interface contém todos os métodos comuns que serão implementados
 * por cada memória, caches e memória principal.
 * */
public interface FuncoesMemorias {

	/**
	 * Método faz a verificação de uma determinada posição de memória.
	 *
	 * @param endereco - endereço da memória principal
	 * @return - true se encontrou dados alocados na memória
	 */
	public boolean verificarPosicaoDeMemoria(Integer endereco);
	/**
	 * Método para acessar o valor de uma posição de uma determinada memória.
	 * 
	 * @param endereco - endereço da memória principal
	 * @return - valor de uma determinada posição da memória
	 */
	public Integer getValorDe(Integer endereco);
	/**
	 * Método para setar o valor de uma posição de uma determinada memória.
	 * 
	 * @param endereco - endereço da memória principal
	 * @param valor - novo valor
	 */
	public void setValorDe(Integer endereco,Integer valor);
	/**
	 * Método seta a memória com -1 em todas as posições, o que significa que a posição está
	 * vazia.
	 */
	public void limparMemoria();
	
	/**
	 * Mapeamento direto.
	 *
	 * @param enderecoRam the endereco ram
	 * @return - retorna o calculo do mapeamento direto em uma determinada memória.
	 */
	public Integer mapeamentoDireto(Integer enderecoRam);
	
	/**
	 * Método faz a verificação de uma determinada posição de memória.
	 *
	 * @param endereco - endereço da memória principal
	 * @param valor - valor para comparação do dado em uma determinada posição damemória
	 * @return - true se encontrou um determinado dado alocado na memória
	 */
	boolean verificarPosicaoDeMemoria(Integer endereco, int valor);
	
	/**
	 * Método retorna o vetor de inteiros que representa a memória.
	 *
	 * @return the memoria
	 */
	public int[] getMemoria();
	int mapeamentoDireto(int enderecoRam);
}
