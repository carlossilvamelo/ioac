package simulador.teste;

import simulador.componentes.MemoriaPrincipal;

public class Testes {

	public static void main(String[] args) {
		String caminho = "C:/Users/Administrator/Desktop/entrada.txt";
		MemoriaPrincipal RAM = new MemoriaPrincipal();
		RAM.limparMemoria();
		RAM.carregarMemoriaRam(caminho);
		System.out.println(RAM.getValorDe(4));
		
		
	}

}
