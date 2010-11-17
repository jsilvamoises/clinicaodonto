package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import classes.Cliente;
import classes.Data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TabelasSaidasMensaisDAO {
	private final static String SEPARADOR = System
			.getProperty("file.separator");
	private final static String CAMINHO = "arquivos" + SEPARADOR
			+ "saidasMensais" + SEPARADOR;
	private final static String TIPO_DE_ARQUIVO = ".xml";
	private static TabelasSaidasMensaisDAO instancia;
	private static XStream xstream = new XStream(new DomDriver());

	private TabelasSaidasMensaisDAO() {
	}

	public static synchronized TabelasSaidasMensaisDAO getInstance() {
		if (instancia == null)
			instancia = new TabelasSaidasMensaisDAO();
		return instancia;

	}

	public void criar(Object[][] tabela) throws Exception, IOException {
		if (tabela == null)
			throw new Exception("Tabela não pôde ser salva");
		File file = new File(CAMINHO + new Data().dataInPersistenceMonth()
				+ TIPO_DE_ARQUIVO);
		file.getParentFile().mkdirs();
		xstream.toXML(tabela, new FileOutputStream(file));
	}

	public Object[] recuperaTabelas() throws Exception {
		List<Object[][]> tabelas = new ArrayList<Object[][]>();
		for (File arquivo : arrayDosArquivos()) {

			if (arquivo.getName().endsWith(TIPO_DE_ARQUIVO)) {
				arquivo.getParentFile().mkdirs();
				Object[][] tabela = (Object[][]) xstream
						.fromXML(new FileInputStream(arquivo));
				tabelas.add(tabela);
			}
		}
		if (tabelas.isEmpty())
			throw new Exception("Nome do Cliente não identificado");

		Object[] tabs = new Cliente[tabelas.size()];
		for (int i = 0; i < tabelas.size(); i++)
			tabs[i] = tabelas.get(i);
		return tabs;
	}

	public void limparTabelas() {
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.getName().endsWith(TIPO_DE_ARQUIVO)) {
				System.gc();
				arquivo.delete();
			}
		}
	}

	private File[] arrayDosArquivos() {
		File file = new File(CAMINHO);
		file.mkdirs();
		return file.listFiles();
	}

	public static void main(String[] args) {
		TabelasSaidasMensaisDAO dao = TabelasSaidasMensaisDAO.getInstance();
		Object[][] tab = new Object[2][2];
		try {
			dao.criar(tab);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
