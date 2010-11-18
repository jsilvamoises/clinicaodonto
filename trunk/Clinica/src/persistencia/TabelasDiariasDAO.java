package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	private static XStream xstream = new XStream(new DomDriver("ISO-8859-1"));

	private TabelasDiariasDAO() {
	}

	public static synchronized TabelasDiariasDAO getInstance() {
		if (instancia == null)
			instancia = new TabelasDiariasDAO();
		return instancia;

	}

	public void criar(Object[][] entradas, Object[][] saidas) throws Exception,
			IOException {
		if (entradas == null || saidas == null)
			throw new Exception("Tabela nãoo pôde ser salva");
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

	public String[] recuperarDatas() throws Exception {
		List<String> datas = new ArrayList<String>();
		for (File file : arrayDosArquivos()) {
			if (file.getName().endsWith(TIPO_DE_ARQUIVO)) {
				file.getParentFile().mkdirs();
				String nomeData = file.getName().replace(TIPO_DE_ARQUIVO, "");
				datas.add(nomeData);
			}
		}
		if (datas.isEmpty())
			throw new Exception("Nenhuma tabela foi criada");
		return listToArray(datas);
	}

	private String[] listToArray(List<String> lista) {
		String[] retorno = new String[lista.size()];
		for (int i = 0; i < retorno.length; i++) {
			retorno[i] = lista.get(i);
		}
		return retorno;
	}

	public void limparTabelas() {
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.getName().endsWith(TIPO_DE_ARQUIVO)) {
				System.gc();
				arquivo.delete();
			}
		}
	}

	public Object[][][] recuperaTupla(String data) throws FileNotFoundException {
		for (File file : arrayDosArquivos()) {
			if (file.getName().endsWith(TIPO_DE_ARQUIVO)
					&& file.getName().startsWith(data)) {
				return (Object[][][]) xstream
						.fromXML(new FileInputStream(file));
			}
		}
		return null;
	}

	public void atualizar(Object[][] entradas, Object[][] saidas, String data)
			throws Exception {
		if (entradas == null || saidas == null || data == null)
			throw new Exception("Não pôde atualizar a tabela");
		File file = new File(CAMINHO + data + TIPO_DE_ARQUIVO);
		file.getParentFile().mkdirs();
		Object[][][] tupla = new Object[2][][];
		tupla[0] = entradas;
		tupla[1] = saidas;
		xstream.toXML(tupla, new FileOutputStream(file));
	}

	private File[] arrayDosArquivos() {
		File file = new File(CAMINHO);
		file.mkdirs();
		return file.listFiles();
	}
}
