package simulador.componentes;

import java.util.ArrayList;

public class Processador{

	private ArrayList<Core> cores = new ArrayList<>();
	private CacheL2 cacheL2 = new CacheL2();

	public Processador(){
		cores.add(new Core());
		cores.add(new Core());
	}
	public CacheL2 getCacheL2(){
		return cacheL2;
	}

	public ArrayList<Core> getCoreList(){
		return cores;
	}

}
