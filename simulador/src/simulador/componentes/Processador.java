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
	 * Método dá acesso a cacheL2 interna ao processador.
	 * 
	 * @return - cache associada eo processador
	 */
	public CacheL2 getCacheL2(){
		return cacheL2;
	}
	
	/**
	 * Método dá acesso aos cores do processador.
	 *
	 * @return - lista dos cores internos ao processador
	 */
	public ArrayList<Core> getCoreList(){
		return cores;
	}
	

}
