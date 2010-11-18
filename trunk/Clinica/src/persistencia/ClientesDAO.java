package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import classes.Cliente;
import classes.Codigo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Classe DAO que cria, deleta, atualiza e recupera clientes ({@link Cliente})
 * no BD.
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 */

public class ClientesDAO {

	private final static String SEPARADOR = System
			.getProperty("file.separator");
	private final static String CAMINHO = "arquivos" + SEPARADOR + "clientes"
			+ SEPARADOR;
	private final static String TIPO_DE_ARQUIVO = ".xml";
	private static ClientesDAO instancia;
	private static XStream xstream = new XStream(new DomDriver("ISO-8859-1"));

	private ClientesDAO() {
	}

	/**
	 * Recupera uma instancia unica para este objeto {@link ClientesDAO}
	 * 
	 * @return A instancia unica para este objeto {@link ClientesDAO}
	 */
	public static synchronized ClientesDAO getInstance() {
		if (instancia == null)
			instancia = new ClientesDAO();
		return instancia;

	}

	/**
	 * Cria um {@link Cliente} no formato de arquivo xml
	 * 
	 * @param cliente
	 *            O {@link Cliente} a ser criado
	 * @throws ArgumentInvalidException
	 *             Caso o cliente passado como parametro seja null
	 * @throws IOException
	 *             Caso haja um problema ao gerar o arquivo xml
	 */
	public void criar(Cliente cliente) throws Exception, IOException {
		if (cliente == null)
			throw new Exception("Cliente inválido");
		File file = new File(CAMINHO + cliente + TIPO_DE_ARQUIVO);
		file.getParentFile().mkdirs();
		xstream.toXML(cliente, new FileOutputStream(file));
	}

	/**
	 * Apaga um {@link Cliente} no formato de arquivo xml
	 * 
	 * @param cliente
	 *            O {@link Cliente} a ser apagado
	 * @throws ArgumentInvalidException
	 *             Caso o cliente passado como parametro seja null ou nao exista
	 *             como dado persistente
	 */
	public void deletar(Cliente cliente) throws Exception {
		if (cliente == null
				|| !(new File(CAMINHO + cliente + TIPO_DE_ARQUIVO).exists()))
			throw new Exception("Cliente_nao pode ser removido");
		File file = new File(CAMINHO + cliente + TIPO_DE_ARQUIVO);
		file.getParentFile().mkdirs();
		System.gc();
		file.delete();
	}

	/**
	 * Recupera todos os clientes ({@link Cliente}) como forma de {@link List}
	 * 
	 * @return Uma {@link List} contento todos os clientes ({@link Cliente})
	 *         persistentes
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public List<Cliente> recuperaClientes() throws FileNotFoundException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.getName().endsWith(TIPO_DE_ARQUIVO)) {
				arquivo.getParentFile().mkdirs();
				Cliente cliente = (Cliente) xstream
						.fromXML(new FileInputStream(arquivo));
				clientes.add(cliente);
			}
		}
		return clientes;
	}

	/**
	 * Atualiza as informacoes do {@link Cliente} passado como parametro a
	 * partir de um {@link Cliente} atualizado
	 * 
	 * @param cliente
	 *            O {@link Cliente} a ser atualizado
	 * @param clienteAtualizado
	 *            O {@link Cliente} atualizado
	 * @throws ArgumentInvalidException
	 *             Caso o {@link Cliente} a ser atualizado ou o {@link Cliente}
	 *             atualizado sejam null, ou o {@link Cliente} a ser atualizado
	 *             nao exista de forma persistente
	 * @throws IOException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public void atualizar(Cliente cliente, Cliente novoCliente) throws Exception, IOException {
		if (cliente == null
				|| !(new File(CAMINHO + cliente + TIPO_DE_ARQUIVO).exists()))
			throw new Exception("Cliente não pode ser atualizado");
		System.gc();
		deletar(cliente);
		criar(novoCliente);
	}

        public void atualizar(Cliente cliente) throws Exception {
            if (cliente == null
				|| !(new File(CAMINHO + cliente + TIPO_DE_ARQUIVO).exists()))
			throw new Exception("Cliente não pode ser atualizado");
		System.gc();
		deletar(cliente);
		criar(cliente);
        }

	/**
	 * Limpa todos os arquivos contendo os clientes {@link Cliente}
	 */
	public void limparClientes() {
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.getName().endsWith(TIPO_DE_ARQUIVO)) {
				System.gc();
				arquivo.delete();
			}
		}
	}

	/**
	 * Recupera um array dos arquivos contidos no path dos clientes
	 * 
	 * @return O array dos arquivos contidos no path dos clientes
	 */
	private File[] arrayDosArquivos() {
		File file = new File(CAMINHO);
		file.mkdirs();
		return file.listFiles();
	}

	public Cliente recuperaCliente(Codigo codigo) throws Exception {
		if (codigo == null)
			throw new Exception("Código inválido");
		for (File f : arrayDosArquivos()) {
			String idF = f.getName().split(" ")[0];
			if (idF.equals(codigo.getCodigo().trim()))
				return (Cliente) xstream.fromXML(new FileInputStream(f));
		}
		throw new Exception("Cliente não identificado");
	}

	public Cliente[] recuperaCliente(String nome) throws Exception {
		if (nome == null)
			throw new Exception("Nome inválido");
		List<Cliente> lista = new ArrayList<Cliente>();

		for (File f : arrayDosArquivos()) {
			if (f.getName().endsWith(TIPO_DE_ARQUIVO)) {
				String[] array = f.getName().split(" ");
				for (int i = 1; i < array.length; i++) {
					if (array[i].replace(TIPO_DE_ARQUIVO, "").toLowerCase()
							.startsWith(nome.toLowerCase())) {
						lista.add((Cliente) xstream
								.fromXML(new FileInputStream(f)));
						break;
					}
				}
			}
		}

		if (lista.isEmpty())
			throw new Exception("Nome do Cliente não identificado");

		Cliente[] clientes = new Cliente[lista.size()];
		for (int i = 0; i < lista.size(); i++)
			clientes[i] = lista.get(i);
		return clientes;
	}

	public void validaCodigo(Codigo codigo) throws Exception {
		for (File f : arrayDosArquivos()) {
			String codigoF = f.getName().split(" ")[0];
			if (codigoF.equals(codigo.getCodigo())) {
				Cliente c = (Cliente) xstream.fromXML(new FileInputStream(f));
				throw new Exception("Este CÓDIGO já existe para o cliente: "
						+ c.getNome());
			}
		}
	}
	
//	public static void main(String[] args) {
//		ClientesDAO d = ClientesDAO.getInstance();
//		Cliente p;
//		try {
//			p = new Cliente("ademar", new Codigo("11"), "", "", new Data("15/06/1991"), "", "", "",
//					new Endereco("", "", "", "", "", ""), Estado.Acre,
//					Sexo.MASCULINO, EstadoCivil.Divorciado,
//					StatusCliente.ABANDONO, StatusFinanceiro.EM_DIA);
//			Cliente x = d.recuperaCliente(new Codigo("11"));
//			System.out.println(x.getCPF());
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
}
