package simulador.componentes;

/**
 * Esta classe representa o componente Core 
 */
public class Core {
	
	public CacheL1 cacheL1 = new CacheL1();
	
	/**
	 * Método para acesso a cache interna ao core
	 * @return cacheL1 - cache interna ao core.
	 */
	public CacheL1 getCacheL1(){
		return cacheL1;
	}
	
}
