package simulador.historico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
/**
 * Classe implementa a conexão e manipulação do banco de dados histórico
 *
 */
public class ConexaoMySql {
	private static boolean status = false;
	private static String mensagem = "";   //variavel que vai informar o status da conexao
	private static Connection con = null;  //variavel para conexao
	private static Statement statement;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;

	private static String servidor = "ioac.cnjd9lqkjtki.us-west-2.rds.amazonaws.com:8080";
	private static String nomeDoBanco = "log";
	private static String usuario = "ioac";
	private static String senha = "ioac3573101";

	public ConexaoMySql(){}



	/**
	 * Método tenta conexão com o banco de dados.
	 * 
	 * @return conexão
	 */
	public static Connection conectar(){
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://" + servidor + "/" + nomeDoBanco;
			setCon((Connection) DriverManager.getConnection(url, usuario, senha));
			status = true;
		}
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e){
			JOptionPane.showMessageDialog(null, "Conexão com o histórico de acessos falhou\nVerifique sua conexão com a internet");
			// System.out.println(e.toString());
		}
		return getCon();
	}

	/**
	 * Método busca no banco de dados o número atual de leituras feitas no simulador
	 * 
	 * @return Número de leitura atual.
	 */
	public static int getValorAtualLeiturasHistorico(){
		int valorAtual=0;

		try {
			String sql = "select leitura from tipoacesso";
			//createStatement de con para criar o Statement
			preparedStatement = getCon().prepareStatement(sql);

			// Definido o Statement, executamos a query no banco de dados
			setResultSet(preparedStatement.executeQuery());


			while(getResultSet().next()){
				valorAtual = Integer.parseInt(getResultSet().getString("leitura"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return valorAtual;
	}

	/**
	 * Método busca no banco de dados o número atual de escritas feitas no simulador
	 * 
	 * @return Número de escritas atual.
	 */
	public static int getValorAtualEscritasHistorico(){
		int valorAtual=0;

		try {
			String sql = "select escrita from tipoacesso";
			//createStatement de con para criar o Statement
			preparedStatement = getCon().prepareStatement(sql);

			// Definido o Statement, executamos a query no banco de dados
			setResultSet(preparedStatement.executeQuery());



			while(getResultSet().next()){

				valorAtual = Integer.parseInt(getResultSet().getString("escrita"));


			}


		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return valorAtual;
	}

	/**
	 * Método atualiza o número leituras no simulador.
	 * @param valorAtual Número atual de leituras
	 */
	public static void atualizarValorLeiturasHistorico(int valorAtual){
		try {
			valorAtual++;
			String sql = "update tipoacesso set leitura = "+valorAtual+" where id=1";
			//createStatement de con para criar o Statement
			preparedStatement = getCon().prepareStatement(sql);

			// Definido o Statement, executamos a query no banco de dados
			preparedStatement.executeUpdate();


		} catch (SQLException ex) {
			ex.printStackTrace();

		}
	}
	/**
	 * Método atualiza o número escritas no simulador.
	 * @param valorAtual Número atual de escritas
	 */
	public static void atualizarValorEscritasHistorico(int valorAtual){
		try {
			valorAtual++;
			String sql = "update tipoacesso set escrita = "+valorAtual+" where id=1";
			//createStatement de con para criar o Statement
			preparedStatement = getCon().prepareStatement(sql);

			// Definido o Statement, executamos a query no banco de dados
			preparedStatement.executeUpdate();


		} catch (SQLException ex) {
			ex.printStackTrace();

		}
	}

	/**
	 * Método insere os dados de cada acesso no banco de dados histórico.
	 * 
	 * @param core Número do core acessado
	 * @param endereco Endereço da memória principal acessado
	 * @param enderecoCacheL1 Endereço da cacheL1 acessado por mapeamento direto
	 * @param enderecoCacheL2 Endereço da cacheL2 acessado por mapeamento direto
	 * @param cachel1status Status da cache no acesso (MISS ou HIT)
	 * @param cachel2status Status da cache no acesso (MISS ou HIT)
	 */
	public static void insertAcessosHistorico(int core, int endereco,int enderecoCacheL1,int enderecoCacheL2, String cachel1status, String cachel2status){

		try {
			String sql = "insert into acessos (core,indexram,indexcachel1,indexcachel2,cachel1status,cachel2status) values ("+core+","+endereco+","+enderecoCacheL1+","+enderecoCacheL2+","+"\""+cachel1status+"\""+","+"\""+cachel2status+"\""+")";

			//createStatement de con para criar o Statement
			preparedStatement = getCon().prepareStatement(sql);

			// Definido o Statement, executamos a query no banco de dados
			preparedStatement.executeUpdate();


		} catch (SQLException ex) {
			ex.printStackTrace();

		}
	}

	/**
	 * Método acessa os dados de todos os acessos no banco de dados e calcula taxas e números totais de 
	 * escritas, leituras e acessos.
	 * 
	 * @return String com os dados da análise
	 */
	public static String analiseHistorico(){
		float taxaMissL1=0;
		float taxaMissL2=0;
		float taxaHitL1=0;
		float taxaHitL2=0;
		float numMissL1=0;
		float numMissL2=0;
		float numHitL1=0;
		float numHitL2=0;
		int totalDeLeituras=getValorAtualLeiturasHistorico();
		int totalDeEscritas=getValorAtualEscritasHistorico();
		int totalDeAcessos= totalDeEscritas+totalDeLeituras;



		try {
			String sql = "select cachel1status,cachel2status from acessos";
			//createStatement de con para criar o Statement
			preparedStatement = getCon().prepareStatement(sql);

			// Definido o Statement, executamos a query no banco de dados
			setResultSet(preparedStatement.executeQuery());

			while(getResultSet().next()){
				if(getResultSet().getString("cachel1status").equals("miss"))
					numMissL1++;
				else
					numHitL1++;

				if(getResultSet().getString("cachel2status").equals("miss"))
					numMissL2++;
				else
					numHitL2++;


			}
			taxaMissL1 = (numMissL1/totalDeAcessos)*100;
			taxaMissL2 = (numMissL2/totalDeAcessos)*100;

			taxaHitL1 = (numHitL1/totalDeAcessos)*100;
			taxaHitL2 = (numHitL2/totalDeAcessos)*100;


			//mostrando analise
			String analise ="";
			analise+= "Total de acessos: "+totalDeAcessos+"\n\n";
			analise+= "Total de escritas: "+totalDeEscritas+"\n\n";
			analise+= "Total de leituras: "+totalDeLeituras+"\n\n";
			analise+= "Cache L1:\nTaxa de MISS: "+taxaMissL1+"% Taxa de HIT: "+taxaHitL1+"%\n\n";
			analise+= "Cache L2:\nTaxa de MISS: "+taxaMissL2+"% Taxa de HIT: "+taxaHitL2+"%\n\n";

			return analise;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}


	}
	/**
	 * Método acessa o banco de dados e gera uma lista de strings que será usada para gerar o arquivo
	 * de log.
	 * 
	 * @return lista de Strings que representam os dados de cada acesso
	 */
	public static ArrayList<String> getCampos(){
		ArrayList<String> lista = new ArrayList<>();

		try {
			String sql = "select * from acessos";
			//createStatement de con para criar o Statement
			preparedStatement = getCon().prepareStatement(sql);

			// Definido o Statement, executamos a query no banco de dados
			setResultSet(preparedStatement.executeQuery());


			String aux="";
			while(getResultSet().next()){
				aux += "Acesso:  "+getResultSet().getString("id")+" |  ";
				aux += "Core:  "+getResultSet().getString("core")+" |  ";
				aux += "Index Ram:  "+getResultSet().getString("indexram")+" |  ";
				aux += "Index CacheL1:  "+getResultSet().getString("indexcachel1")+" |  ";
				aux += "Index CacheL2:  "+getResultSet().getString("indexcachel2")+" |  ";
				aux += "CacheL1 Status:  "+getResultSet().getString("cachel1status")+" |  ";
				aux += "CacheL2 Status:  "+getResultSet().getString("cachel2status");

				lista.add(aux);
				aux="";
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		return lista;

	}
	

    /**
	 * encerra a conexão corrente
	 * @return boolean
	 */
	public static boolean fecharConexao(){
		try {
			if((getResultSet() != null) && (statement != null)){
				getResultSet().close();
				statement.close();
			}
			getCon().close();
			return true;
		} catch(SQLException e) {
			//JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return false;
	}



	/**
	 * @return the status
	 */
	public static boolean isStatus() {
		return status;
	}

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @return the statement
	 */
	public Statement getStatement() {
		return statement;
	}

	/**
	 * @return the resultSet
	 */
	public static ResultSet getResultSet() {
		return resultSet;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * @return the con
	 */
	public static Connection getCon() {
		return con;
	}

	/**
	 * @param con1 the con to set
	 */
	public static void setCon(Connection con1) {
		con = con1;
	}

	/**
	 * @param statement1 the statement to set
	 */
	public void setStatement(Statement statement1) {
		statement = statement1;
	}

	/**
	 * @param resultSet1 the resultSet to set
	 */
	public static void setResultSet(ResultSet resultSet1) {
		resultSet = resultSet1;
	}

	/**
	 * @return the servidor
	 */
	public String getServidor() {
		return servidor;
	}

	/**
	 * @param servidor the servidor to set
	 */
	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	/**
	 * @return the nomeDoBanco
	 */
	public String getNomeDoBanco() {
		return nomeDoBanco;
	}

	/**
	 * @param nomeDoBanco the nomeDoBanco to set
	 */
	public void setNomeDoBanco(String nomeDoBanco) {
		this.nomeDoBanco = nomeDoBanco;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

}

