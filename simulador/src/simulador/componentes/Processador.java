package simulador.componentes;

import java.util.ArrayList;

/**
 * Esta classe representa o componente "Processador", com 2 cores internos e uma cacheL2.
 */
public class Processador{

	private ArrayList<Core> cores = new ArrayList<>();
	
	private CacheL2 cacheL2 = new CacheL2();

	
	public Processador(){
		cores.add(new Core());
		cores.add(new Core());
	}
	/**
	 * M�todo d� acesso a cacheL2 interna ao processador.
	 * 
	 * @return - cache associada eo processador
	 */
	public CacheL2 getCacheL2(){
		return cacheL2;
	}
	
	/**
	 * M�todo d� acesso aos cores do processador.
	 *
	 * @return - lista dos cores internos ao processador
	 */
	public ArrayList<Core> getCoreList(){
		return cores;
	}
	
	/**
	 * Este m�todo representa a rotina que vai ser chamada no final de cada acesso, a lista de processadores
	 * vai ser varrida, verificando core a core se j� existe aquele valor da posi��o de mem�ria acessada.
	 * Todos ser�o atualizados em caso de MISS.
	 * */
	
	

}
