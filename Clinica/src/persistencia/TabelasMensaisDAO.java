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

public class TabelasMensaisDAO {
	private final static String SEPARADOR = System
			.getProperty("file.separator");
	private final static String CAMINHO = "arquivos" + SEPARADOR
			+ "entradasMensais" + SEPARADOR;
	private final static String TIPO_DE_ARQUIVO = ".xml";
	private static TabelasMensaisDAO instancia;
	private static XStream xstream = new XStream(new DomDriver());

	private TabelasMensaisDAO() {
	}

	public static synchronized TabelasMensaisDAO getInstance() {
		if (instancia == null)
			instancia = new TabelasMensaisDAO();
		return instancia;

	}

	public void criar(Object[][] entradas, Object[][] saidas) throws Exception, IOException {
		if (entradas == null || saidas == null)
			throw new Exception("Tabela não pôde ser salva");
		File file = new File(CAMINHO + new Data().dataInPersistenceMonth()
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
				Object[][][] tabela = (Object[][][]) xstream
						.fromXML(new FileInputStream(arquivo));
				tabelas.add(tabela);
			}
		}
		
		if (tabelas.isEmpty())
			throw new Exception("Nenhuma tabela mensal foi criada");
		return tabelas;
	}
	
	public String[] recuperarTabelasPorData() throws Exception {
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
		return (String[]) listToArray(datas);
	}
	
	@SuppressWarnings("unchecked")
	private Object[] listToArray(List lista) {
		Object[] retorno = new Object[lista.size()];
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
			if (file.getName().endsWith(TIPO_DE_ARQUIVO) && 
					file.getName().startsWith(data)) {
				return (Object[][][]) xstream
				.fromXML(new FileInputStream(file));
			}
		}
		return null;
	}

	private File[] arrayDosArquivos() {
		File file = new File(CAMINHO);
		file.mkdirs();
		return file.listFiles();
	}
}
