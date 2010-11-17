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
		Object[][][] tupla = {entradas, saidas};
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
		TabelasMensaisDAO dao = TabelasMensaisDAO.getInstance();
		Object[][] tab = new Object[2][2];
		try {
			dao.criar(tab);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
