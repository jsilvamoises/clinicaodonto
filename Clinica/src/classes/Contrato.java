package classes;

import java.util.ArrayList;


import enums.StatusContrato;
import enums.TipoDeContrato;

/**
 * Classe que representa um Contrato
 * 
 * @author giovanicb
 * 
 */
public class Contrato {

	private String id;
	private Data terminoDoContrato, inicioDoContrato;
	private Cliente contratante;
	private double preco;
	private TipoDeContrato tipoDoContrato;
	private final static int NUMERO_DE_MESES = 12;
	private int mesAPagar;
	private int numeroParcelas;
	private StatusContrato status;
	private ArrayList<String> boleto;
	private ArrayList<String> datas;

	public Contrato(Cliente cliente) {
		this(cliente, 0.0, null, null, null, null);
	}

	public Contrato(Cliente cliente, double preco, Data inicioDoContrato,
			Data terminoDoContrato, TipoDeContrato tipoDoContrato,
			StatusContrato status) {
		setContratante(cliente);
		setPreco(preco);
		setId();
		setInicioDoContrato(inicioDoContrato);
		setTerminoDoContrato(terminoDoContrato);
		setTipoDeContrato(tipoDoContrato);
		mesAPagar = inicioDoContrato.getMes() + 1;
		numeroParcelas = 0;
		this.status = status;
	}

	public TipoDeContrato getTipoDoContrato() {
		return tipoDoContrato;
	}

	public void setTipoDoContrato(TipoDeContrato tipoDoContrato) {
		this.tipoDoContrato = tipoDoContrato;
	}

	public StatusContrato getStatus() {
		return status;
	}

	public void setStatus(StatusContrato status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId() {
		this.id = String.valueOf(hashCode());
	}

	public Data getTerminoDoContrato() {
		return terminoDoContrato;
	}

	public void setTerminoDoContrato(Data terminoDoContrato) {
		this.terminoDoContrato = terminoDoContrato;
	}

	/**
	 *Retorna a data de realizacao do contrato.
	 */
	public Data getInicioDoContrato() {
		return inicioDoContrato;
	}

	public void setInicioDoContrato(Data inicioDoContrato) {
		this.inicioDoContrato = inicioDoContrato;
	}

	/**
	 * Retorna o cliente que tem esse contrato.
	 */
	public Cliente getContratante() {
		return contratante;
	}

	public void setContratante(Cliente contratante) {
		this.contratante = contratante;
	}

	/**
	 * Retorna o valor a ser pago.
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * Ajusta o preco a ser pago
	 * 
	 * @param preco
	 *            O preco a ser ajustado a pagar
	 */
	public void setPreco(double preco) {
		if(preco != 0)
			this.preco = preco;
		this.preco = 70.0;
	}

	public void setTipoDeContrato(TipoDeContrato tipoDoContrato) {
		this.tipoDoContrato = tipoDoContrato;
	}

	public TipoDeContrato getTipoDeContrato() {
		return tipoDoContrato;
	}

	/**
	 * Recupera a duração em meses para este contrato
	 * 
	 * @return A duração em meses para este contrato
	 */
	public int getDuracaoDoContrato() {
		int contador = 0;
		int anoI = getInicioDoContrato().getAno();
		int anoF = getTerminoDoContrato().getAno();

		int mesI = getInicioDoContrato().getMes();
		int mesF = getTerminoDoContrato().getMes();
		while (anoI != anoF || mesI != mesF) {
			if (mesI == 12) {
				anoI++;
				mesI = 0;
			}
			mesI++;
			contador++;
		}
		return contador;

	}

	public void efetuaPagamento() throws Exception {
		if (numeroParcelas < getDuracaoDoContrato()) {
			if (mesAPagar + 1 < 12) {
				mesAPagar++;
			} else {
				mesAPagar = 1;
			}
			numeroParcelas++;
		} else {
			throw new Exception("Todos os pagamentos foram efetuados.");
		}

	}

	public int getNumeroDeParcelasNaoPagas() {
		return getDuracaoDoContrato() - numeroParcelas;
	}
	
	public int getParcelaAtual(){
		return numeroParcelas;
	}

	public int getMesAPagar() {
		return mesAPagar;
	}

	/**
	 * Retorna a data completa a do proximo pagamento, não apenas o mês.
	 * 
	 * @return
	 */
	public String getUltimaDataVencimento() {
		boleto = datasEmPeriodo(getDuracaoDoContrato());
		return boleto.get(numeroParcelas);
	}

	public ArrayList<String> datasEmPeriodo(int periodoEmMeses) {
        datas = new ArrayList<String>();
        int dia = getInicioDoContrato().getDia();
        int mes = getInicioDoContrato().getMes() + 1;
        int ano = getInicioDoContrato().getAno();
        String date = dia + "/" + mes + "/" + ano;
        datas.add(date);
        for (int i = 0; i < periodoEmMeses; i++) {
                mes++;
                if (getInicioDoContrato().getDia() > getInicioDoContrato()
                                .numeroDiasDoMes(mes, ano)) {
                        dia = getInicioDoContrato().numeroDiasDoMes(mes, ano);
                } else {
                        dia = getInicioDoContrato().getDia();
                }

                if (mes > 12) {
                        mes = 1;
                        ano++;
                }
                datas.add(dia + "/" + mes + "/" + ano);
        }
        return datas;
}

	/*public static void main(String[] args) {
		Cliente p;
		try {
			Data e = new Data("29/12/2010");
			Data f = new Data("29/12/2011");
			p = new Cliente("xico", "", "", "15/06/1991", "", "", "", "",
					new Endereco("", "", "", "", "", ""), Sexo.MASCULINO,
					Estado.Acre, EstadoCivil.Divorciado,
					StatusCliente.ABANDONO, StatusFinanceiro.EM_DIA);
			Contrato x = new Contrato(p, 100, e, f, TipoDeContrato.ORTODONTIA,
					StatusContrato.EM_TRATAMENTO);
			int j = x.getNumeroDeParcelasNaoPagas();
			for (int i = 0; i < j; i++) {
				System.out.println("mes:" + x.getMesAPagar());
				System.out
						.println("parcela:" + x.getNumeroDeParcelasNaoPagas());
				System.out.println("data:" + x.getUltimaDataVencimento());
				x.efetuaPagamento();

			}

		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

}
