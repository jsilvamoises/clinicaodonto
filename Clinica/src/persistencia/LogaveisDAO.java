package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import interfaces.Logavel;

import classes.Login;
import classes.Senha;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LogaveisDAO {
	private final static String SEPARADOR = System
			.getProperty("file.separator");
	private final static String CAMINHO = "arquivos" + SEPARADOR + "logaveis"
			+ SEPARADOR;
	private final static String TIPO_DE_ARQUIVO = ".xml";
	private static LogaveisDAO instancia;
	private static XStream xstream = new XStream(new DomDriver("ISO-8859-1"));

	private LogaveisDAO() {
	}

	/**
	 * Recupera uma instancia unica para este objeto {@link LogaveisDAO}
	 * 
	 * @return A instancia unica para este objeto {@link LogaveisDAO}
	 */
	public static synchronized LogaveisDAO getInstance() {
		if (instancia == null)
			instancia = new LogaveisDAO();
		return instancia;

	}

	/**
	 * Cria um {@link Logavel} no formato de arquivo xml
	 * 
	 * @param logavel
	 *            O {@link Logavel} a ser criado
	 * @throws ArgumentInvalidException
	 *             Caso o logavel passado como parametro seja null
	 * @throws IOException
	 *             Caso haja um problema ao gerar o arquivo xml
	 */
	public void criar(Logavel logavel) throws Exception, IOException {
		if (logavel == null)
			throw new Exception("Logavel inválido");
		File file = new File(CAMINHO + logavel.getLogin().getLogin()
				+ TIPO_DE_ARQUIVO);
		file.getParentFile().mkdirs();
		xstream.toXML(logavel, new FileOutputStream(file));
	}

	/**
	 * Apaga um {@link Logavel} no formato de arquivo xml
	 * 
	 * @param logavel
	 *            O {@link Logavel} a ser apagado
	 * @throws ArgumentInvalidException
	 *             Caso o logavel passado como parametro seja null ou nao exista
	 *             como dado persistente
	 */
	public void deletar(Logavel logavel) throws Exception {
		if (logavel == null)
			throw new Exception("Logavel não pôde ser removido");
		File file = new File(CAMINHO + logavel.getLogin().getLogin()
				+ TIPO_DE_ARQUIVO);
		file.getParentFile().mkdirs();
		System.gc();
		file.delete();
	}

	/**
	 * Recupera todos os logavels ({@link Logavel}) como forma de {@link List}
	 * 
	 * @return Uma {@link List} contento todos os logavels ({@link Logavel})
	 *         persistentes
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public List<Logavel> recuperaLogaveis() throws FileNotFoundException {
		List<Logavel> logavels = new ArrayList<Logavel>();
		for (File arquivo : arrayDosArquivos()) {

			if (arquivo.getName().endsWith(TIPO_DE_ARQUIVO)) {
				arquivo.getParentFile().mkdirs();
				Logavel logavel = (Logavel) xstream
						.fromXML(new FileInputStream(arquivo));
				logavels.add(logavel);
			}
		}
		return logavels;
	}

	/**
	 * Atualiza as informacoes do {@link Logavel} passado como parametro a
	 * partir de um {@link Logavel} atualizado
	 * 
	 * @param logavel
	 *            O {@link Logavel} a ser atualizado
	 * @param logavelAtualizado
	 *            O {@link Logavel} atualizado
	 * @throws ArgumentInvalidException
	 *             Caso o {@link Logavel} a ser atualizado ou o {@link Logavel}
	 *             atualizado sejam null, ou o {@link Logavel} a ser atualizado
	 *             nao exista de forma persistente
	 * @throws IOException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public void atualizar(Logavel logavel, Logavel novo) throws Exception,
			IOException {
		if (logavel == null
				|| !(new File(CAMINHO + logavel.getLogin().getLogin()
						+ TIPO_DE_ARQUIVO).exists()))
			throw new Exception("Logavel não pode ser atualizado");
		System.gc();
		this.deletar(logavel);
		this.criar(novo);
	}

	/**
	 * Limpa todos os arquivos contendo os logavels {@link Logavel}
	 */
	public void limparLogaveis() {
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.getName().endsWith(TIPO_DE_ARQUIVO)) {
				System.gc();
				arquivo.delete();
			}
		}
	}

	public Logavel recuperaLogavel(Login login, Senha senha) throws Exception {
		if (login == null
				|| senha == null
				|| !(new File(CAMINHO + login.getLogin() + TIPO_DE_ARQUIVO)
						.exists()))
			throw new Exception("Login inválido");
		File file = new File(CAMINHO + login.getLogin() + TIPO_DE_ARQUIVO);
		file.getParentFile().mkdirs();
		Logavel logavel = (Logavel) xstream.fromXML(new FileInputStream(file));
		if (!logavel.getSenha().getSenha().equals(senha.getSenha()))
			throw new Exception("Senha inválida");
		return logavel;
	}

	/**
	 * Recupera um array dos arquivos contidos no path dos logavels
	 * 
	 * @return O array dos arquivos contidos no path dos logavels
	 */
	private File[] arrayDosArquivos() {
		File file = new File(CAMINHO);
		file.mkdirs();
		return file.listFiles();
	}
}
