package facades;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import persistencia.ClientesDAO;
import persistencia.LogaveisDAO;
import classes.Cliente;
import classes.Codigo;
import classes.Contrato;
import classes.Data;
import classes.Endereco;
import classes.Senha;
import enums.Estado;
import enums.EstadoCivil;
import enums.Sexo;
import enums.StatusCliente;
import enums.StatusContrato;
import enums.StatusFinanceiro;
import enums.TipoDeContrato;
import interfaces.Logavel;

/**
 * Fachada que provê a comunicação da GUI com as classes
 * 
 * @author Giovani
 * 
 */
public class SistemaFacade {
	private static SistemaFacade instacia;

	/**
	 * Construtor privado
	 */
	private SistemaFacade() {
	}

	/**
	 * Recupera a instancia unica para esta fachada
	 * 
	 * @return A instancia unica para esta fachada
	 */
	public static SistemaFacade getInstance() {
		if (instacia == null)
			return new SistemaFacade();
		return instacia;
	}

	public void cadastrarCliente(String nome, String codigo, String cPF,
			String rG, String dataDeNascimento, String telefone,
			String email, String profissao, String rua, String numero,
			String complemento, String bairro, String cidade, String cep,
			Sexo sexo, Estado estado, EstadoCivil estadoCivil,
			StatusCliente status, StatusFinanceiro statusFinanceiro)
			throws IOException, Exception {

		ClientesDAO dao = ClientesDAO.getInstance();
		Codigo cod = new Codigo(codigo);
		dao.validaCodigo(cod);

		Cliente cliente = new Cliente(nome, cod, cPF, rG, new Data(
				dataDeNascimento), telefone, profissao, email, new Endereco(
				rua, numero, complemento, bairro, cidade, cep), estado, sexo,
				estadoCivil, status, statusFinanceiro);

		dao.criar(cliente);
	}

        public void atualizarCliente(String nome, String codigo, String cPF,
			String rG, String dataDeNascimento, String telefone,
			String email, String profissao, String rua, String numero,
			String complemento, String bairro, String cidade, String cep,
			Sexo sexo, Estado estado, EstadoCivil estadoCivil,
			StatusCliente status, StatusFinanceiro statusFinanceiro)
			throws IOException, Exception {

		ClientesDAO dao = ClientesDAO.getInstance();
		Cliente cliente = new Cliente(nome, new Codigo(codigo), cPF, rG, new Data(
				dataDeNascimento), telefone, profissao, email, new Endereco(
				rua, numero, complemento, bairro, cidade, cep), estado, sexo,
				estadoCivil, status, statusFinanceiro);
		dao.atualizar(cliente);
	}

	public Logavel verificaLogin(String login, String senha) throws Exception {
		LogaveisDAO logavelDao = LogaveisDAO.getInstance();
		return logavelDao.recuperaLogavel(new classes.Login(login), new Senha(senha));
	}
       
	public Cliente[] recuperaClientePorNome(String nome) throws Exception {
		return ClientesDAO.getInstance().recuperaCliente(nome);
	}

	public Cliente recuperaClientePorID(String id) throws Exception {
		try {
			Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new Exception("Deverá ser passado um número");
		}
		return ClientesDAO.getInstance().recuperaCliente(new Codigo(id));
	}

	public Estado[] carregaEstados() {
		return Estado.values();
	}
	
	//ESSE NAO TEM EM_TRATAMENTO POIS É USADO NO SET
	public StatusContrato[] carregaStatusContrato(){
		StatusContrato[] lista = new StatusContrato[2];
		lista[0] = StatusContrato.CANCELADO;
		lista[1] = StatusContrato.CONCLUIDO;
		return lista;
	}

	public EstadoCivil[] carregaEstadosCivis() {
		return EstadoCivil.values();
	}

	public Sexo[] carregaSexos() {
		return Sexo.values();
	}

	public StatusCliente[] carregaStatusCliente() {
		return StatusCliente.values();
	}

	public StatusFinanceiro[] carregaStatusFinanceiro() {
		return StatusFinanceiro.values();
	}
	
	//ESSE TEM TRATAMENTO PQ É USADO PRA VERIFICACAO
	public StatusContrato[] carregaStatusContratoCompleto(){
		return StatusContrato.values();
	}
	
	public void efetuaPagamento(Cliente c) throws Exception{
		c.efetuaPagamento();
		ClientesDAO dao = ClientesDAO.getInstance();
		dao.atualizar(c);
	}
	
	public void criaContrato(String dataI, String dataF, String idCliente, double preco) throws Exception{
		Cliente cliente = recuperaClientePorID(idCliente);
		Data inicio = new Data(dataI);
		Data termino = new Data(dataF);
		
		if(inicio.afterOrEquals(termino)){
			throw new Exception("A data de inicio deve ser anterior a do termino!");

		}
		for (Contrato c : cliente.getContratos()) {
			if(c.getStatus() == StatusContrato.EM_TRATAMENTO)
				throw new Exception("O cliente já possui um contrato em andamento!");
		}
        Contrato contrato = new Contrato(cliente, preco, inicio, termino, TipoDeContrato.ORTODONTIA, StatusContrato.EM_TRATAMENTO);
        
        cliente.addContrato(contrato);
        ClientesDAO dao = ClientesDAO.getInstance();
        dao.atualizar(cliente);

	}
	
	public void editarCadastro(String idCliente, String statusContrato) throws Exception{
		Cliente cliente = recuperaClientePorID(idCliente);
		Contrato contrato = cliente.getContrato();
		if(contrato == null){
			throw new Exception("O Usuário não possui contrato em andamento!");
		}
		contrato.setStatus(StatusContrato.getStatusContrato(statusContrato));
		ClientesDAO dao = ClientesDAO.getInstance();
	    dao.atualizar(cliente);
	}
	
	public List<Cliente> listaContratosPorData(String dataI, String dataF) throws Exception{
		ClientesDAO dao = ClientesDAO.getInstance();
		List<Cliente> listaClientes = dao.recuperaClientes();
		
		Data inicio = new Data(dataI);
		Data termino = new Data(dataF);
		
		if(inicio.afterOrEquals(termino)){
			throw new Exception("A data de inicio deve ser anterior a do termino!");
		}
		if(listaClientes == null){
			throw new Exception("Ainda não existe nenhum cliente cadastrado!");			
		}
		
		List<Cliente> listaClientesPorData = new ArrayList<Cliente>();
		
		for (Cliente cliente : listaClientes) {
			if(cliente.getContrato() != null)
				if(cliente.getContrato().getTerminoDoContrato().beforeOrEquals(termino) && cliente.getContrato().getInicioDoContrato().afterOrEquals(inicio)){
					listaClientesPorData.add(cliente);
				}
		}
		
		if(listaClientesPorData.isEmpty()){
			throw new Exception("Não existe nenhum contrato nesse período!");		
		}
		return listaClientesPorData;
			
		
	}
	
	public Cliente[] listaContratosPorStatus(String status) throws Exception{
		ClientesDAO dao = ClientesDAO.getInstance();
		List<Cliente> listaClientes = dao.recuperaClientes();
		List<Cliente> listaClientesPorStatus = new ArrayList<Cliente>();
		StatusContrato statusDoContrato = StatusContrato.getStatusContrato(status);

		for (Cliente cliente : listaClientes) {
			if(cliente.getContratoComStatus(statusDoContrato) != null)
				listaClientesPorStatus.add(cliente);
		}
		
		if(listaClientesPorStatus.isEmpty()){
			throw new Exception("Não existe nenhum contrato com esse Status");
		}
                
                Cliente[] listaClientesComStatus = new Cliente[listaClientesPorStatus.size()];
                
                for (int i = 0; i < listaClientesPorStatus.size(); i++) {
                    listaClientesComStatus[i] = listaClientesPorStatus.get(i);
                }
                return listaClientesComStatus;
            }


}