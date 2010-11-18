package classes;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

import interfaces.User;
import enums.Estado;
import enums.EstadoCivil;
import enums.Privilegio;
import enums.Sexo;
import enums.StatusCliente;
import enums.StatusContrato;
import enums.StatusFinanceiro;

/**
 * Classe que implementa um cliente.
 * 
 * @author Tiago
 * 
 */
public class Cliente implements User {
	private String nome, CPF, RG, telefone, email, profissao;
	private Data dataDeNascimento;
	private Codigo codigo;
	private Endereco endereco;
	private Sexo sexo;
	private Estado estado;
	private EstadoCivil estadoCivil;
	private ArrayList<Contrato> contrato;
	private Senha senha;
	private Login login;
	private Privilegio privilegio;
	private StatusCliente status;
	private StatusFinanceiro financeiro;

	public Cliente(String nome, Codigo codigo, String cpf, String rg,
			Data dataDeNascimento, String telefone, String profissao,
			String email, Endereco endereco, Estado estado, Sexo sexo,
			EstadoCivil estadoCivil, StatusCliente status,
			StatusFinanceiro financeiro) throws Exception {

		setNome(nome);
		setCodigo(codigo);
		setCPF(cpf);
		setRG(rg);
		setDataDeNascimento(dataDeNascimento);
		setTelefone(telefone);
		setProfissao(profissao);
		setEmail(email);
		setEndereco(endereco);
		setEstado(estado);
		setSexo(sexo);
		setEstadoCivil(estadoCivil);
		setStatus(status);
		setFinanceiro(financeiro);
		contrato = new ArrayList<Contrato>();
	}

	public StatusCliente getStatus() {
		return status;
	}

	public void setStatus(StatusCliente status) {
		this.status = status;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) throws Exception {
		if (endereco == null)
			throw new Exception("Endereço inválido");
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return a lista de todos os contratos que os usuarios tem.
	 */
	public ArrayList<Contrato> getContratos() {
		return contrato;
	}

	@Override
	/**
	 Retorna o nome do cliente.
	 */
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(String nome) throws Exception {
		if (nome == null || nome.trim().isEmpty())
			throw new Exception("O campo NOME deve ser preenchido");
		this.nome = nome;
	}

	@Override
	public Codigo getCodigo() {
		return codigo;
	}

	@Override
	public void setCodigo(Codigo codigo) throws Exception {
		this.codigo = codigo;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public Data getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Data dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) throws Exception {
		this.sexo = sexo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) throws Exception {
		this.estado = estado;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) throws Exception {
		this.estadoCivil = estadoCivil;
	}

	// Atibuindo que um cliente poderá ser também um Logavel, tipo...
	// a funcionaria X pode ser atendida como cliente do sistema tambem

	@Override
	public Login getLogin() {
		return login;
	}

	@Override
	public Senha getSenha() {
		return senha;
	}

	@Override
	public void setLogin(Login login) throws Exception {
		this.login = login;
	}

	@Override
	public void setSenha(Senha senha) throws Exception {
		this.senha = senha;
	}

	@Override
	public Privilegio getPrivilegio() {
		return privilegio;
	}

	@Override
	public void setPrivilegio(Privilegio privilegio) throws Exception {
		this.privilegio = privilegio;
	}

	@Override
	public String toString() {
		return getCodigo() + " " + getNome();
	}

	public String exibirInformacoes() {
		return "Código: " + getCodigo() + " | Nome: " + getNome() + " | Sexo: "
				+ getSexo() + "\n\nCPF: " + getCPF() + " | RG: " + getRG()
				+ " | Data de Nascimento: " + getDataDeNascimento()
				+ "\n\nTelefone: " + getTelefone() + "\n\nE-mail: "
				+ getEmail() + " | Profissão: " + getProfissao()
				+ "\n\nEndereço " + getEndereco() + "\n\nEstado: "
				+ getEstado() + " | Estado Civil: " + getEstadoCivil();
	}

	public void setFinanceiro(StatusFinanceiro financeiro) {
		this.financeiro = financeiro;
	}

	public StatusFinanceiro getFinanceiro() {
		Iterator<Contrato> it = contrato.iterator();
		while (it.hasNext()) {
			Contrato c = it.next();
			if (c.getStatus() == StatusContrato.EM_TRATAMENTO) {
				if (pagamentoAtrasado(c.getUltimaDataVencimento()))
					setFinanceiro(StatusFinanceiro.ATRASADO);
				else
					setFinanceiro(StatusFinanceiro.EM_DIA);
			} else
				setFinanceiro(StatusFinanceiro.EM_ABERTO);
		}
		return financeiro;
	}

	private boolean pagamentoAtrasado(String data) {
		String[] arrayData = data.split("/");
		GregorianCalendar dataAtual = new GregorianCalendar();
		GregorianCalendar dataVencimento = new GregorianCalendar(Integer
				.parseInt(arrayData[2]), Integer.parseInt(arrayData[1]),
				Integer.parseInt(arrayData[0]));
		if (dataAtual.after(dataVencimento))
			return true;
		return false;
	}

	public void cancelarContrato() {
		for (Contrato c : contrato) {
			if (c.getStatus() == StatusContrato.EM_TRATAMENTO) {
				c.setStatus(StatusContrato.CANCELADO);
			}
		}
	}

	public void concluirContrato() {
		for (Contrato c : contrato) {
			if (c.getStatus() == StatusContrato.EM_TRATAMENTO) {
				c.setStatus(StatusContrato.CONCLUIDO);
			}
		}
	}

	public Contrato getContrato() {
		for (Contrato c : contrato) {
			if (c.getStatus() == StatusContrato.EM_TRATAMENTO) {
				return c;
			}
		}
		return null;
	}

	// NESSE CASO ELE RETORNA TODOS OS CONTRATOS DO CLIENTE
	public Contrato getContratoComStatus(StatusContrato status) {
		for (Contrato c : contrato) {
			if (c.getStatus() == status) {
				return c;
			}
		}
		return null;
	}

	public String getDadosDoContrato() throws Exception {
		Contrato c = getContrato();
		if (c == null) {
			throw new Exception("O Usuário não possui contrato em andamento!");
		}
		String dados = "Data: " + c.getUltimaDataVencimento() + ", Parcela: "
				+ c.getParcelaAtual() + "Valor: " + c.getPreco();
		return dados;
	}

	public void efetuaPagamento() throws Exception {
		Contrato c = getContrato();
		if (c == null) {
			throw new Exception("O Cliente não possui contrato em andamento!");
		}
		boolean pagou = c.efetuaPagamento();
		if(!pagou)
			throw new Exception("Todos os pagamentos ja foram efetuados");
	}

	public void addContrato(Contrato contrato) throws Exception {
		for (Contrato c : this.contrato) {
			if (c.getStatus() == StatusContrato.EM_TRATAMENTO)
				throw new Exception(
						"O cliente já possui um contrato em andamento");
		}
		this.contrato.add(contrato);
	}
}
