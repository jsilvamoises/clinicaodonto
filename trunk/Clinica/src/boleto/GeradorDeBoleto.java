package boleto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import classes.Cliente;
import classes.Contrato;

public class GeradorDeBoleto {
	private Cliente cliente;
	private Contrato contrato;
	private BufferedReader inputStream = null;
	private PrintWriter outputStream = null;
	

	public GeradorDeBoleto(Cliente cliente) {
		this.cliente = cliente;
		contrato = cliente.getContrato();
	}

	public void GerarBoleto() throws Exception {
		if (cliente == null)
			throw new Exception("Cliente inválido");
		if (contrato == null)
			throw new Exception("Cliente não possui contrato válido");

		inputStream = new BufferedReader(new FileReader(

		"boleto.rtf"));

		outputStream = new PrintWriter(new FileWriter(cliente.getCodigo() + ".rtf"));

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
					line = line
							.replace("N_PARCELA", String.valueOf(contador));
					aux++;
				}else{
					aux = 0;
					line = line
					.replace("N_PARCELA", String.valueOf(contador));
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
					line = line
							.replace("DATA_VENCIMENTO", String.valueOf(listaDeVencimentos.get(index)));
					aux2++;
				}else{
					aux2 = 0;
					line = line
					.replace("DATA_VENCIMENTO", String.valueOf(listaDeVencimentos.get(index)));
					index++;
				}
			}
			if (line.contains("VALOR_CONTRATO")) {
				line = line.replace("VALOR_CONTRATO", String.valueOf(contrato.getPreco()));
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
