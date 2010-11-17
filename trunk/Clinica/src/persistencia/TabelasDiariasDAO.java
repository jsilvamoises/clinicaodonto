package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import classes.Data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TabelasDiariasDAO {
	private final static String SEPARADOR = System
			.getProperty("file.separator");
	private final static String CAMINHO = "arquivos" + SEPARADOR
			+ "tabelasDiarias" + SEPARADOR;
	private final static String TIPO_DE_ARQUIVO = ".xml";
	private static TabelasDiariasDAO instancia;
	private static XStream xstream = new XStream(new DomDriver());

	private TabelasDiariasDAO() {
	}

	public static synchronized TabelasDiariasDAO getInstance() {
		if (instancia == null)
			instancia = new TabelasDiariasDAO();
		return instancia;

	}

	public void criar(Object[][] entradas, Object[][] saidas) throws Exception, IOException {
		if (entradas == null || saidas == null)
			throw new Exception("Tabela não pôde ser salva");
		File file = new File(CAMINHO + new Data().dataInPersistenceDiary()
				+ TIPO_DE_ARQUIVO);
		file.getParentFile().mkdirs();
		Object[][][] tupla = new Object[2][][];
		tupla[0] = entradas;
		tupla[1] = saidas;
		xstream.toXML(tupla, new FileOutputStream(file));
	}

	public List<Object[][][]> recuperaTabelas() throws Exception {
		List<Object[][][]> tabelas = new ArrayList<Object[][][]>();
		for (File arquivo : arrayDosArquivos()) {

			if (arquivo.getName().endsWith(TIPO_DE_ARQUIVO)) {
				arquivo.getParentFile().mkdirs();
				Object[][][] tupla = (Object[][][]) xstream
						.fromXML(new FileInputStream(arquivo));
				tabelas.add(tupla);
			}
		}
		if (tabelas.isEmpty())
			throw new Exception("Nenhuma tabela foi salva");
		return tabelas;
	}
	
	public String[] recuperarTabelasPorData() {
		
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
		TabelasDiariasDAO dao = TabelasDiariasDAO.getInstance();
		Object[][] entradas = {{3}, {4}};
		Object[][] saidas = {{5}, {6}};
		try {
			dao.criar(entradas, saidas);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
