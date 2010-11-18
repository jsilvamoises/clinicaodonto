package boleto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import classes.Cliente;
import classes.Contrato;

public class GeradorDeBoletos {
	private Cliente cliente;
	private Contrato contrato;
	private BufferedReader inputStream = null;
	private PrintWriter outputStream = null;
	private static final String SEPARATOR =  System.getProperty("file.separator");
	
	public GeradorDeBoletos(Cliente cliente) {
		this.cliente = cliente;
		contrato = cliente.getContrato();
	}

	public void GerarBoleto() throws Exception {
		if (cliente == null)
			throw new Exception("Cliente inválido");
		if (contrato == null)
			throw new Exception("Cliente não possui contrato válido");
		if (contrato.getDuracaoDoContrato() == 12) {
			inputStream = new BufferedReader(new FileReader(

			"boleto.rtf"));
		} else if (contrato.getDuracaoDoContrato() == 24) {
			inputStream = new BufferedReader(new FileReader(

			"boleto2.rtf"));
		} else {
			inputStream = new BufferedReader(new FileReader(

			"boleto3.rtf"));
		}
		
		new File("Boletos" + SEPARATOR).mkdirs();
        FileWriter writer = new FileWriter("Boletos" + SEPARATOR + cliente.getCodigo() + ".rtf");
        outputStream = new PrintWriter(writer);

		String line;
		int contador = 1;
		int index = 0;
		int aux = 0;
		int aux2 = 0;
		ArrayList<String> listaDeVencimentos = contrato.datasEmPeriodo(12);

		while ((line = inputStream.readLine()) != null) {
			if (line.contains("N_CLIENTE")) {
				line = line.replace("N_CLIENTE", String.valueOf(cliente
						.getCodigo()));
			}
			if (line.contains("N_PARCELA")) {
				if (aux == 0) {
					line = line.replace("N_PARCELA", String.valueOf(contador));
					aux++;
				} else {
					aux = 0;
					line = line.replace("N_PARCELA", String.valueOf(contador));
					contador++;
				}
			}

			if (line.contains("NOME_CLIENTE")) {
				line = line.replace("NOME_CLIENTE", cliente.getNome());
			}
			if (line.contains("ENDERECO_CLIENTE")) {
				line = line.replace("ENDERECO_CLIENTE", cliente.getEndereco()
						.toString());
			}
			if (line.contains("DATA_VENCIMENTO")) {
				if (aux2 == 0) {
					line = line.replace("DATA_VENCIMENTO", String
							.valueOf(listaDeVencimentos.get(index)));
					aux2++;
				} else {
					aux2 = 0;
					line = line.replace("DATA_VENCIMENTO", String
							.valueOf(listaDeVencimentos.get(index)));
					index++;
				}
			}
			if (line.contains("VALOR_CONTRATO")) {
				line = line.replace("VALOR_CONTRATO", String.valueOf(contrato
						.getPreco()));
			}
			outputStream.println(line);

		}

		if (inputStream != null) {

			inputStream.close();

		}

		if (outputStream != null) {

			outputStream.close();

		}

	}

}
