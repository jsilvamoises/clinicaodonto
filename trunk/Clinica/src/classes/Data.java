package classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Classe que inicializa uma data.
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * 
 */
public class Data {

	private static final long serialVersionUID = 1L;
	private static final int DIAS_DO_MES = 31;
	private static final int MESES_DO_ANO = 12;
	private static int ANO_ATUAL;
	private Calendar data;

	/**
	 * Construtor default da classe Data
	 */
	public Data() {
		ANO_ATUAL = new GregorianCalendar().get(Calendar.YEAR);
		data = new GregorianCalendar();
	}

	/**
	 * Construtor da classe Data
	 * 
	 * @param data
	 *            {@link String}
	 * @throws Exception 
	 * @throws Exception
	 *             caso a cada seja invalida
	 */
	public Data(String data) throws Exception {
		ANO_ATUAL = new GregorianCalendar().get(Calendar.YEAR);
		setData(data);
	}

	/**
	 * Metodo que verifica a validade de configuracao de uma data.
	 * 
	 * @param data
	 *            {@link String}
	 * @return True caso a data seja valida
	 */
	public boolean verificaData(String data) {
		if (data == null || !data.matches("\\d\\d\\/\\d\\d\\/\\d\\d\\d\\d")
				|| numeroDiasDoMes(Integer.valueOf(data.substring(3, 5)), Integer.valueOf(data.substring(6, 10))) < Integer.valueOf(data.substring(0, 2))) {
			return false;
		}
		return true;
	}

	/**
	 * Metodo que converte de Calendar para String no formato "dd/MM/yyyy"
	 * 
	 * @param data
	 *            {@link Calendar}
	 * @return String data *dd/MM/yyyy*
	 */
	public String calendarToString(Calendar data) {
		String strdate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if (data != null) {
			strdate = sdf.format(data.getTime());
		}
		return strdate;
	}

	/**
	 * Metodo usado para converter uma String num Calendar.
	 * 
	 * @param data
	 *            {@link String}
	 * @return Calendar data
	 * @throws ParseException
	 *             caso nao consiga converter.
	 */
	private Calendar conversorData(String dat) throws ParseException {
		String data = dat;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(data));
		return cal;

	}

	/**
	 * Metodo acessador de uma data
	 * 
	 * @return Calendar data
	 */
	public Calendar getData() {
		return data;
	}

	/**
	 * Metodo que seta uma data
	 * 
	 * @param data
	 *            {@link String} nova
	 * @throws Exception 
	 * @throws ArgumentInvalidException
	 *             caso a data seja invalida
	 */
	public void setData(String data) throws Exception {
		if (!verificaData(data)) {
			throw new Exception("Data inválida!");
		}

		try {
			Calendar date = conversorData(data);
			this.data = date;
		} catch (Exception e) {
			this.data = null;
		}		
	}

	/**
	 * Metodo que retorna a data de hoje
	 * 
	 * @return String data
	 */
	public String todaysDate() {
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(data);
	}

	/**
	 * Metodo que recupera o número do mês para esta data de 0 a 11
	 * 
	 * @return O número do mês para esta data
	 */
	public int getMes() {
            if(data != null)
		return getData().get(Calendar.MONTH);
            return -1;
	}

	/**
	 * Método que recupera o ano desta Data
	 * 
	 * @return O ano desta Data
	 */
	public int getAno() {
            if(data != null)
		return getData().get(Calendar.YEAR);
            return -1;
	}
	
	/**
	 * Metodo que recupera o dia.
	 * @return
	 */
	public int getDia(){
            if(data != null)
		return getData().get(Calendar.DAY_OF_MONTH);
            return -1;
	}
	
	/**
	 * Verifica se as datas são iguais ou depois da passada pelo parametro.
	 * @param data
	 * @return
	 */
	public boolean afterOrEquals(Data data){
		boolean boleano = false;
		GregorianCalendar dataThis = new GregorianCalendar(getAno(), getMes(), getDia());
		GregorianCalendar dataRecebida = new GregorianCalendar(data.getAno(), data.getMes(), data.getDia());
		if(dataThis.compareTo(dataRecebida) == 0)
			boleano = true;
		if (dataThis.after(dataRecebida) == true || boleano == true)
			return true;
		return false;
	}
	/**
	 * Verifica se as datas são iguais ou antes da passada pelo parametro.
	 * @param data
	 * @return
	 */
	public boolean beforeOrEquals(Data data){
		boolean boleano = false;
		GregorianCalendar dataThis = new GregorianCalendar(getAno(), getMes(), getDia());
		GregorianCalendar dataRecebida = new GregorianCalendar(data.getAno(), data.getMes(), data.getDia());
		if(dataThis.compareTo(dataRecebida) == 0)
			boleano = true;
		if (dataThis.before(dataRecebida) == true || boleano == true)
			return true;
		return false;
	}
	
	public boolean after(Data data){
		GregorianCalendar dataThis = new GregorianCalendar(getAno(), getMes(), getDia());
		GregorianCalendar dataRecebida = new GregorianCalendar(data.getAno(), data.getMes(), data.getDia());
		return dataThis.after(dataRecebida);
	}
	
	public boolean before(Data data){
		GregorianCalendar dataThis = new GregorianCalendar(getAno(), getMes(), getDia());
		GregorianCalendar dataRecebida = new GregorianCalendar(data.getAno(), data.getMes(), data.getDia());
		return dataThis.before(dataRecebida);
	}

	
	public void setMes(int mes) throws Exception{
		if (!(mes <= 12 && mes >= 0))
			if(mes == 12)
				setData(getDia() + "/" + 0 + "/" + getAno() + 1);
			else
				setData(getDia() + "/" + mes + "/" + getAno());
		else
			throw new Exception("Não foi possível modificar o mês.");
	}
	
	public ArrayList<String> datasEmPeriodo(int periodoEmMeses){
		ArrayList<String> datas = new ArrayList<String>();
		int dia = getDia();
		int mes = getMes() + 1;
		int ano = getAno();
		String date = dia + "/" + mes + "/" + ano;
		datas.add(date);
		for (int i = 0; i < periodoEmMeses; i++){
			mes++;
			if(getDia() > numeroDiasDoMes(mes, ano)){
				dia = numeroDiasDoMes(mes, ano);
			}else{
				dia = getDia();
			}
				
			if(mes > 12){
				mes = 1;
				ano++;
			}
			datas.add(dia + "/" + mes + "/" + ano);
		}
		return datas;
	}
	
	public int numeroDiasDoMes(int mes, int ano) {
		switch (mes) {
		case 1:
			return 31;
		case 2:
			if (ano % 400 == 0 || (ano % 4 == 0 && ano % 100 != 0)){
				return 29;
			}
			return 28;
		case 3:
			return 31;
		case 4:
			return 30;
		case 5:
			return 31;
		case 6:
			return 30;
		case 7:
			return 31;
		case 8:
			return 31;
		case 9:
			return 30;
		case 10:
			return 31;
		case 11:
			return 30;
		case 12:
			return 31;
			

		default:
			return 31;
		}
	}

    @Override
    public String toString() {
        return getData() != null ? getDia() + "/" + getMes() + "/" + getAno() : "";
    }



	/*public static void main(String[] args) {
		Data x = null;
		try {
			x = new Data("29/12/2010");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(x.datasEmPeriodo(12));
	}*/

}
