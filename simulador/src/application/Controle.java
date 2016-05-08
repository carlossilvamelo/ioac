package application;


import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.security.auth.Destroyable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import simulador.componentes.CacheL1;
import simulador.componentes.Core;
import simulador.componentes.MemoriaPrincipal;
import simulador.componentes.Processador;


/**
 * Nesta classe estão todos os eventos da interface gráfica, manipulação de dados
 * de entrada e os processos do projeto. A classe implementa uma interface necessária para
 * a inicialização do controle da interface.
 * 
 * */

public class Controle implements Initializable{

	//componentes da interface
	@FXML
	private ListView<String> listView;
	
	@FXML
	private ListView<String> viewRam;
	
	@FXML
	private Button btnCarregarMem;
	
	@FXML
	private Button btnEnviar;
	
	@FXML
	private TextField entradaPosiMem;
	
	@FXML
	private TextField entradaNCores;
	
	@FXML
	private TextField entradaNumCore;
	
	@FXML
	private TextField entradaNovoValor;
	
	@FXML
	private TextField entradaArquivoMem;
	
	@FXML
	private Text labelStatusL1;
	
	@FXML
	private Text labelStatusL2;
	
	@FXML
	private Text labelAcessos;
	
	@FXML
	private RadioButton radioButtonLeitura;
	
	@FXML
	private RadioButton radioButtonEscrita;



	//Objetos funcionais do programa
	MemoriaPrincipal RAM;
	
	ArrayList<Processador> processadores = new ArrayList<>();



	/**
	 * Método de inicialização do controle da interface, bem como para inicialização de
	 * instâncias de objetos da parte funcional do programa.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RAM = new MemoriaPrincipal();
	}





	//============================================ METODOS FUNCIONAIS DO PROGRAMA =======================



	/**
	 * Método faz todo o processo de verificação e atualização de um acesso de LEITURA.
	 *
	 * @param entradaCore the entrada core
	 * @param entradaEndereco the entrada endereco
	 */
	public void acessoLeitura(int entradaCore,int entradaEndereco){
		int processador,core;

		//buscando processador do core de entrada
		int auxProcessador = entradaCore/2;
		int resto = entradaCore%2;

		if(resto==0){
			processador = auxProcessador;
			core = 0;
		}else{
			processador=auxProcessador;
			core=1;
		}
		//verificações de acesso	
		if(processadores.get(processador).getCoreList().get(core).getCacheL1().verificarPosicaoDeMemoria(entradaEndereco,RAM.getValorDe(entradaEndereco))){
			//mostra no status
			labelStatusL1.setText("CacheL1 HIT!");
			labelStatusL2.setText("");
		}else{
			//verificar dados
			labelStatusL1.setText("CacheL1 MISS!");

			//buscando na cacheL2
			if(processadores.get(processador).getCacheL2().verificarPosicaoDeMemoria(entradaEndereco,RAM.getValorDe(entradaEndereco))){
				//HIT cacheL2
				RAM.setValorDe(entradaEndereco,RAM.getValorDe(entradaEndereco));
				processadores.get(processador).getCoreList().get(core).getCacheL1().setValorDe(entradaEndereco, RAM.getValorDe(entradaEndereco));
				labelStatusL2.setText("CacheL2 HIT!");
			}else{
				//miss cacheL2
				processadores.get(processador).getCoreList().get(core).getCacheL1().setValorDe(entradaEndereco, RAM.getValorDe(entradaEndereco));
				processadores.get(processador).getCacheL2().setValorDe(entradaEndereco, RAM.getValorDe(entradaEndereco));
				labelStatusL2.setText("CacheL2 MISS!");
			}

		}

	}

	/**
	 * Método faz todo o processo de verificação e atualização de um acesso de ESCRITA.
	 *
	 * @param entradaCore the entrada core
	 * @param entradaEndereco the entrada endereco
	 * @param novoDado the novo dado
	 */
	public void acessoEscrita(int entradaCore,int entradaEndereco, int novoDado){
		int processador,core;

		//buscando processador do core de entrada
		int auxProcessador = entradaCore/2;
		int resto = entradaCore%2;

		if(resto==0){
			processador = auxProcessador;
			core = 0;
		}else{
			processador=auxProcessador;
			core=1;
		}
		//verificações de acesso	
		if(processadores.get(processador).getCoreList().get(core).getCacheL1().verificarPosicaoDeMemoria(entradaEndereco,RAM.getValorDe(entradaEndereco))){
			//mostra no status
			processadores.get(processador).getCoreList().get(core).getCacheL1().setValorDe(entradaEndereco, novoDado);
			processadores.get(processador).getCacheL2().setValorDe(entradaEndereco, novoDado);
			RAM.setValorDe(entradaEndereco, novoDado);


			labelStatusL1.setText("CacheL1 HIT!");
			labelStatusL2.setText("");
		}else{
			labelStatusL1.setText("CacheL1 MISS!");


			//buscando na cacheL2
			if(processadores.get(processador).getCacheL2().verificarPosicaoDeMemoria(entradaEndereco,RAM.getValorDe(entradaEndereco))){
				//hit cacheL2 seta novo valor
				RAM.setValorDe(entradaEndereco, novoDado);
				processadores.get(processador).getCoreList().get(core).getCacheL1().setValorDe(entradaEndereco, novoDado);
				labelStatusL2.setText("CacheL2 HIT!");
			}else{

				processadores.get(processador).getCoreList().get(core).getCacheL1().setValorDe(entradaEndereco, novoDado);
				processadores.get(processador).getCacheL2().setValorDe(entradaEndereco, novoDado);
				RAM.setValorDe(entradaEndereco, novoDado);

				labelStatusL2.setText("CacheL2 MISS!");
			}

		}

	}


	/**
	 * Método aloca os processadores, dada a quantidade de cores, se temos n cores então teremos n/2 
	 * processadores, dado que cada processador tem 2 cores.
	 * 
	 */
	public void alocarProcessadores(){
		//se existem n cores, existem n/2 processadores
		//criando os processadores
		int numProcessadores = (int)(Integer.parseInt(entradaNCores.getText())/2);
		for (int i = 0; i < numProcessadores; i++) {
			processadores.add(new Processador());
		}
	}


	/**
	 * Método Auxilidar para tratar o vetor que vai ser mostrado na saída.
	 * @param vetor - Vetor de inteiros
	 * @return - retorna uma string com o padrão que será mostrado na interface
	 * */
	public String tratarStringVetor(int[] vetor){
		String saida="";
		for (int i = 0; i < vetor.length; i++) {
			if(vetor[i]!= -1)
				saida+="["+i+"] "+vetor[i]+" , ";
			else
				saida+="["+i+"] # , ";
		}

		return saida;
	}
	
	/**
	 * Método cria as listas de strings que vão ser mostradas no listView (interface).
	 */
	public void mostrarSaida(){
		//mostrando as memorias 
		ArrayList<String> listaSaida = new ArrayList<>();
		String saidaN="";
		int contCores=0;
		for (int i = 0; i < processadores.size(); i++) {
			saidaN+="						PROCESSADOR "+i+":\n";
			saidaN+="CORE "+(contCores++)+":\n";
			saidaN+="	Cache L1: "+ tratarStringVetor(processadores.get(i).getCoreList().get(0).getCacheL1().getMemoria())+"\n"; 
			saidaN+="CORE "+(contCores++)+":\n";
			saidaN+="	Cache L1: "+ tratarStringVetor(processadores.get(i).getCoreList().get(1).getCacheL1().getMemoria())+"\n"; 

			saidaN+="\nCache L2: "+ tratarStringVetor(processadores.get(i).getCacheL2().getMemoria());

			listaSaida.add(saidaN);
			saidaN ="";
		}

		dadosSaida(listaSaida);

	}

	/**
	 * Método auxilidar para o método mostrarSaida(), seta o componente listView com a lista adequada para
	 * ser mostrado na saída.
	 *
	 * @param lista the lista
	 */
	public void dadosSaida(ArrayList<String> lista){
		ObservableList<String> data = FXCollections.observableArrayList(lista);
		listView.setItems(data);

	}

	/**
	 * Método cria e seta a lista que vai ser mostrada na saída, mostrando a memória RAM a cada mudança.
	 * */
	public void mostrarRam(){
		ArrayList<String> lista = new ArrayList<>();
		String mem ="RAM: "+tratarStringVetor(RAM.getMemoria());
		lista.add(mem);
		ObservableList<String> ram = FXCollections.observableArrayList(lista);
		viewRam.setItems(ram);
	}





	//======================================  EVENTOS DA INTERFACE ============================



	/**
	 * evento do botão carregar memória.
	 *
	 * @param event the event
	 */
	public void carregarMemoria(ActionEvent event){
		//C:/Users/Administrator/Desktop/entradaRAM.txt
		RAM.carregarMemoriaRam(entradaArquivoMem.getText());
		alocarProcessadores();
		mostrarRam();

	}

	/**
	 * evento do botão enviar, faz a verificação das entradas e chama os metodos equivalentes.
	 */
	public int acesso=2;
	
	/**
	 * Enviar acesso.
	 *
	 * @param event the event
	 */
	public void enviarAcesso(ActionEvent event){

		int numCore = Integer.parseInt(entradaNumCore.getText());
		if(radioButtonLeitura.isSelected()){

			acessoLeitura(Integer.parseInt(entradaNumCore.getText()), Integer.parseInt(entradaPosiMem.getText()));

		}else{
			if(radioButtonEscrita.isSelected()){
				acessoEscrita(Integer.parseInt(entradaNumCore.getText()), Integer.parseInt(entradaPosiMem.getText()),Integer.parseInt(entradaNovoValor.getText()));
			}

		}

		mostrarSaida();
		mostrarRam();
		labelAcessos.setText("Entradas acesso "+(acesso++));

	}

}//fim classe
