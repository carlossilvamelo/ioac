package simulador.teste;

import simulador.componentes.CacheL1;
import simulador.componentes.CacheL2;
import simulador.componentes.MemoriaPrincipal;
import simulador.outros.Historico;

public class Testes {

	public static void main(String[] args) {
	//	String caminho = "C:/Users/Administrator/Desktop/entrada.txt";
		//MemoriaPrincipal RAM = new MemoriaPrincipal();
	//	RAM.limparMemoria();
	//	RAM.carregarMemoriaRam(caminho);
		//System.out.println(RAM.getValorDe(4));
		
	//	CacheL2 cachel2 = new CacheL2();
		//System.out.println(cachel2.mapeamentoDireto(35));
		Historico historico = new Historico();
		historico.teste();
		
	}

}
