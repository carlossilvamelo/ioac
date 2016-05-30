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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import simulador.componentes.CacheL1;
import simulador.componentes.CacheL2;
import simulador.componentes.Core;
import simulador.componentes.MemoriaPrincipal;
import simulador.componentes.Processador;
import simulador.historico.ArquivoHistorico;
import simulador.historico.ConexaoMySql;


/**
 * Nesta classe est�o todos os eventos da interface gr�fica, manipula��o de dados
 * de entrada e os processos do projeto. A classe implementa uma interface necess�ria para
 * a inicializa��o do controle da interface.
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
	private Button btnGerarLog; 
	@FXML
	private Button btnGerarAnalise;
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
	 * M�todo de inicializa��o do controle da interface, bem como para inicializa��o de
	 * inst�ncias de objetos da parte funcional do programa.
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
	 * M�todo faz todo o processo de verifica��o e atualiza��o de um acesso de LEITURA.
	 *
	 * @param entradaCore the entrada core
	 * @param entradaEndereco the entrada endereco
	 */
	public void acessoLeitura(int entradaCore,int entradaEndereco){
		int processador,core;
		String cacheL1Status="";
		String cacheL2Status="";

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


		//verifica��es de acesso	
		if(processadores.get(processador).getCoreList().get(core).getCacheL1().verificarPosicaoDeMemoria(entradaEndereco,RAM.getValorDe(entradaEndereco))){
			labelStatusL1.setText("CacheL1 HIT!");
			cacheL1Status ="hit";
			labelStatusL2.setText("");
		}else{
			labelStatusL1.setText("CacheL1 MISS!");
			cacheL1Status ="miss";

			//buscando na cacheL2
			if(processadores.get(processador).getCacheL2().verificarPosicaoDeMemoria(entradaEndereco,RAM.getValorDe(entradaEndereco))){
				//HIT cacheL2
				RAM.setValorDe(entradaEndereco,RAM.getValorDe(entradaEndereco));
				processadores.get(processador).getCoreList().get(core).getCacheL1().setValorDe(entradaEndereco, RAM.getValorDe(entradaEndereco));
				labelStatusL2.setText("CacheL2 HIT!");
				cacheL2Status = "hit";
			}else{
				//miss cacheL2
				processadores.get(processador).getCoreList().get(core).getCacheL1().setValorDe(entradaEndereco, RAM.getValorDe(entradaEndereco));
				processadores.get(processador).getCacheL2().setValorDe(entradaEndereco, RAM.getValorDe(entradaEndereco));
				labelStatusL2.setText("CacheL2 MISS!");
				cacheL2Status = "miss";
			}

		}

		CacheL1 l1 = new CacheL1();
		CacheL2 l2 = new CacheL2();
		
		if(ConexaoMySql.isStatus())
			ConexaoMySql.insertAcessosHistorico(entradaCore, entradaEndereco,l1.mapeamentoDireto(entradaEndereco),l2.mapeamentoDireto(entradaEndereco), cacheL1Status, cacheL2Status);
		
		
		atualizacaoDeTodosOsCores(entradaEndereco);
	}

	/**
	 * M�todo faz todo o processo de verifica��o e atualiza��o de um acesso de ESCRITA.
	 *
	 * @param entradaCore the entrada core
	 * @param entradaEndereco the entrada endereco
	 * @param novoDado the novo dado
	 */
	public void acessoEscrita(int entradaCore,int entradaEndereco, int novoDado){
		int processador,core;
		String cacheL1Status="";
		String cacheL2Status="";

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
		//verifica��es de acesso	
		if(processadores.get(processador).getCoreList().get(core).getCacheL1().verificarPosicaoDeMemoria(entradaEndereco,novoDado)){
			processadores.get(processador).getCoreList().get(core).getCacheL1().setValorDe(entradaEndereco, novoDado);
			processadores.get(processador).getCacheL2().setValorDe(entradaEndereco, novoDado);
			RAM.setValorDe(entradaEndereco, novoDado);

			labelStatusL1.setText("CacheL1 HIT!");
			cacheL1Status="hit";

			labelStatusL2.setText("");
		}else{
			labelStatusL1.setText("CacheL1 MISS!");
			cacheL1Status="miss";

			//buscando na cacheL2
			if(processadores.get(processador).getCacheL2().verificarPosicaoDeMemoria(entradaEndereco,novoDado)){
				//hit cacheL2 seta novo valor
				RAM.setValorDe(entradaEndereco, novoDado);
				processadores.get(processador).getCoreList().get(core).getCacheL1().setValorDe(entradaEndereco, novoDado);
				labelStatusL2.setText("CacheL2 HIT!");
				cacheL2Status="hit";
			}else{

				processadores.get(processador).getCoreList().get(core).getCacheL1().setValorDe(entradaEndereco, novoDado);
				processadores.get(processador).getCacheL2().setValorDe(entradaEndereco, novoDado);
				RAM.setValorDe(entradaEndereco, novoDado);

				labelStatusL2.setText("CacheL2 MISS!");
				cacheL2Status="miss";
			}

		}

		CacheL1 l1 = new CacheL1();
		CacheL2 l2 = new CacheL2();
		if(ConexaoMySql.isStatus())
			ConexaoMySql.insertAcessosHistorico(entradaCore, entradaEndereco,l1.mapeamentoDireto(entradaEndereco),l2.mapeamentoDireto(entradaEndereco), cacheL1Status, cacheL2Status);
		
		atualizacaoDeTodosOsCores(entradaEndereco);
	}


	/**
	 * M�todo aloca os processadores, dada a quantidade de cores, se temos n cores ent�o teremos n/2 
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
	 * M�todo Auxilidar para tratar o vetor que vai ser mostrado na sa�da.
	 * @param vetor - Vetor de inteiros
	 * @return - retorna uma string com o padr�o que ser� mostrado na interface
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
	 * M�todo cria as listas de strings que v�o ser mostradas no listView (interface).
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
	 * M�todo auxilidar para o m�todo mostrarSaida(), seta o componente listView com a lista adequada para
	 * ser mostrado na sa�da.
	 *
	 * @param lista the lista
	 */
	public void dadosSaida(ArrayList<String> lista){
		ObservableList<String> data = FXCollections.observableArrayList(lista);
		listView.setItems(data);

	}

	/**
	 * M�todo cria e seta a lista que vai ser mostrada na sa�da, mostrando a mem�ria RAM a cada mudan�a.
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
	 * evento do bot�o carregar mem�ria.
	 *
	 * @param event the event
	 */
	public void carregarMemoria(ActionEvent event){
		RAM.carregarMemoriaRam(entradaArquivoMem.getText());
		alocarProcessadores();
		mostrarRam();

	}

	/**
	 * M�todo acessa o banco de dados hist�rico, buscando todos os dados e colocando em um arquivo texto
	 * @param event (ActionEvent
	 */
	public void gerarLog(ActionEvent event){

		if(ConexaoMySql.isStatus()){//verificando conex�o com o banco de dados
			//gerar arquivo com log
			ArrayList<String> lista = ConexaoMySql.getCampos();
			ArquivoHistorico arquivo = new ArquivoHistorico();
			arquivo.escritor(lista);

			Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
			dialogoAviso.setTitle("log");
			dialogoAviso.setHeaderText("");
			dialogoAviso.setContentText("Arquivo hist�rico criado com sucesso!");
			dialogoAviso.showAndWait();
		}else{
			Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
			dialogoAviso.setTitle("log");
			dialogoAviso.setHeaderText("");
			dialogoAviso.setContentText("N�o foi poss�vel acessar o hist�rico, verifique sua conex�o com a internet e reinicie o simulador!");
			dialogoAviso.showAndWait();

		}



	}
	/**
	 * M�todo acessa os dados do banco de dados hist�rico, buscando os dados, fazendo alguns c�lculos
	 * e mostrando uma an�lise simples dos dados.
	 * 
	 * @param event ActionEvent
	 */
	public void gerarAnalise(ActionEvent event){

		if(ConexaoMySql.isStatus()){//verificando conex�o com o banco de dados
			String analise = ConexaoMySql.analiseHistorico();
			Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
			dialogoAviso.setTitle("An�lise de acessos");
			dialogoAviso.setHeaderText("");
			dialogoAviso.setContentText(analise);
			dialogoAviso.showAndWait();
		}else{
			Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
			dialogoAviso.setTitle("An�lise");
			dialogoAviso.setHeaderText("");
			dialogoAviso.setContentText("N�o foi poss�vel acessar o hist�rico para gerar a an�lise, verifique sua conex�o com a internet e reinicie o simulador!");
			dialogoAviso.showAndWait();
		}
	}

	/**
	 * evento do bot�o enviar, faz a verifica��o das entradas e chama os metodos equivalentes.
	 */
	public int acesso=2;//inicio da contagem depois do acesso 1

	/**
	 * Enviar acesso.
	 *
	 * @param event the event
	 */
	public void enviarAcesso(ActionEvent event){


		int numCore = Integer.parseInt(entradaNumCore.getText());
		if(radioButtonLeitura.isSelected()){
			if(ConexaoMySql.isStatus())//verificando conex�o com o banco de dados
				ConexaoMySql.atualizarValorLeiturasHistorico(ConexaoMySql.getValorAtualLeiturasHistorico());

			acessoLeitura(Integer.parseInt(entradaNumCore.getText()), Integer.parseInt(entradaPosiMem.getText()));

		}else{
			if(radioButtonEscrita.isSelected()){

				if(ConexaoMySql.isStatus())//verificando conex�o com o banco de dados
					ConexaoMySql.atualizarValorEscritasHistorico(ConexaoMySql.getValorAtualEscritasHistorico());

				acessoEscrita(Integer.parseInt(entradaNumCore.getText()), Integer.parseInt(entradaPosiMem.getText()),Integer.parseInt(entradaNovoValor.getText()));
			}

		}

		mostrarSaida();
		mostrarRam();
		labelAcessos.setText("Entradas acesso "+(acesso++));

	}

	/**
	 * M�todo percorre a lista de processadores verificando e atualizando os dados de todos os cores
	 * para a posi��o de mem�ria de entrada.
	 * 
	 * @param enderecoRAM - endere�o da mem�ria principal dado na entrada do programa.
	 */
	public void atualizacaoDeTodosOsCores(int enderecoRAM){

		for (Processador processador : processadores) {

			if(!processador.getCoreList().get(0).getCacheL1().verificarPosicaoDeMemoria(enderecoRAM, RAM.getValorDe(enderecoRAM))){
				//MISS atualiza o valor no core 0 do processador
				processador.getCoreList().get(0).getCacheL1().setValorDe(enderecoRAM, RAM.getValorDe(enderecoRAM));
			}
			if(!processador.getCoreList().get(1).getCacheL1().verificarPosicaoDeMemoria(enderecoRAM, RAM.getValorDe(enderecoRAM))){
				//MISS atualiza o valor no core 1 do processador
				processador.getCoreList().get(1).getCacheL1().setValorDe(enderecoRAM, RAM.getValorDe(enderecoRAM));
			}
		}

	}




}//fim classe
