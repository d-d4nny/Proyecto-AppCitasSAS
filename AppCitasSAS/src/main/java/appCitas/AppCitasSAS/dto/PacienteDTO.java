package appCitas.AppCitasSAS.dto;

import java.util.Calendar;
import java.util.Objects;

public class PacienteDTO {

	//ATRIBUTOS
	private long idPaciente;
	private String nombreCompletoPaciente;
	private String dniPaciente;
	private String tlfPaciente;
	private String emailPaciente;
	private String contrasenaPaciente;
	private Calendar fechaNacimiento;
	private String generoPaciente;
	private String direccionPaciente;
	private String imgPaciente;
	private String token;
	private Calendar expiracionToken;
	private String password;
	private String password2;
	

	
	//GETTERS Y SETTERS
	
	public long getIdPaciente() {
		return idPaciente;
	}


	public void setIdPaciente(long idPaciente) {
		this.idPaciente = idPaciente;
	}


	public String getNombreCompletoPaciente() {
		return nombreCompletoPaciente;
	}


	public void setNombreCompletoPaciente(String nombreCompletoPaciente) {
		this.nombreCompletoPaciente = nombreCompletoPaciente;
	}


	public String getDniPaciente() {
		return dniPaciente;
	}


	public void setDniPaciente(String dniPaciente) {
		this.dniPaciente = dniPaciente;
	}


	public String getTlfPaciente() {
		return tlfPaciente;
	}


	public void setTlfPaciente(String tlfPaciente) {
		this.tlfPaciente = tlfPaciente;
	}


	public String getEmailPaciente() {
		return emailPaciente;
	}


	public void setEmailPaciente(String emailPaciente) {
		this.emailPaciente = emailPaciente;
	}


	public String getContrasenaPaciente() {
		return contrasenaPaciente;
	}


	public void setContrasenaPaciente(String contrasenaPaciente) {
		this.contrasenaPaciente = contrasenaPaciente;
	}


	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getGeneroPaciente() {
		return generoPaciente;
	}


	public void setGeneroPaciente(String generoPaciente) {
		this.generoPaciente = generoPaciente;
	}


	public String getDireccionPaciente() {
		return direccionPaciente;
	}


	public void setDireccionPaciente(String direccionPaciente) {
		this.direccionPaciente = direccionPaciente;
	}


	public String getImgPaciente() {
		return imgPaciente;
	}


	public void setImgPaciente(String imgPaciente) {
		this.imgPaciente = imgPaciente;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Calendar getExpiracionToken() {
		return expiracionToken;
	}


	public void setExpiracionToken(Calendar expiracionToken) {
		this.expiracionToken = expiracionToken;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPassword2() {
		return password2;
	}


	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	

	//CONSTRUCTORES
	
	public PacienteDTO() {
		
	}
	

	public PacienteDTO(long idPaciente, String nombreCompletoPaciente, String dniPaciente, String tlfPaciente,
			String emailPaciente, String contrasenaPaciente, Calendar fechaNacimiento, String generoPaciente,
			String direccionPaciente, String imgPaciente) {
		super();
		this.idPaciente = idPaciente;
		this.nombreCompletoPaciente = nombreCompletoPaciente;
		this.dniPaciente = dniPaciente;
		this.tlfPaciente = tlfPaciente;
		this.emailPaciente = emailPaciente;
		this.contrasenaPaciente = contrasenaPaciente;
		this.fechaNacimiento = fechaNacimiento;
		this.generoPaciente = generoPaciente;
		this.direccionPaciente = direccionPaciente;
		this.imgPaciente = imgPaciente;
	}


	public PacienteDTO(long idPaciente, String nombreCompletoPaciente, String dniPaciente, String tlfPaciente,
			String emailPaciente, String contrasenaPaciente, Calendar fechaNacimiento, String generoPaciente,
			String direccionPaciente, String imgPaciente, String token, Calendar expiracionToken, String password,
			String password2) {
		super();
		this.idPaciente = idPaciente;
		this.nombreCompletoPaciente = nombreCompletoPaciente;
		this.dniPaciente = dniPaciente;
		this.tlfPaciente = tlfPaciente;
		this.emailPaciente = emailPaciente;
		this.contrasenaPaciente = contrasenaPaciente;
		this.fechaNacimiento = fechaNacimiento;
		this.generoPaciente = generoPaciente;
		this.direccionPaciente = direccionPaciente;
		this.imgPaciente = imgPaciente;
		this.token = token;
		this.expiracionToken = expiracionToken;
		this.password = password;
		this.password2 = password2;
	}

	
	
	//METODOS

	@Override
	public int hashCode() {
		return Objects.hash(contrasenaPaciente, direccionPaciente, dniPaciente, emailPaciente, expiracionToken,
				fechaNacimiento, generoPaciente, idPaciente, imgPaciente, nombreCompletoPaciente, password, password2,
				tlfPaciente, token);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PacienteDTO other = (PacienteDTO) obj;
		return Objects.equals(contrasenaPaciente, other.contrasenaPaciente)
				&& Objects.equals(direccionPaciente, other.direccionPaciente)
				&& Objects.equals(dniPaciente, other.dniPaciente) && Objects.equals(emailPaciente, other.emailPaciente)
				&& Objects.equals(expiracionToken, other.expiracionToken)
				&& Objects.equals(fechaNacimiento, other.fechaNacimiento)
				&& Objects.equals(generoPaciente, other.generoPaciente) && idPaciente == other.idPaciente
				&& Objects.equals(imgPaciente, other.imgPaciente)
				&& Objects.equals(nombreCompletoPaciente, other.nombreCompletoPaciente)
				&& Objects.equals(password, other.password) && Objects.equals(password2, other.password2)
				&& Objects.equals(tlfPaciente, other.tlfPaciente) && Objects.equals(token, other.token);
	}


	@Override
	public String toString() {
		return "PacienteDTO [idPaciente=" + idPaciente + ", nombreCompletoPaciente=" + nombreCompletoPaciente
				+ ", dniPaciente=" + dniPaciente + ", tlfPaciente=" + tlfPaciente + ", emailPaciente=" + emailPaciente
				+ ", contrasenaPaciente=" + contrasenaPaciente + ", fechaNacimiento=" + fechaNacimiento
				+ ", generoPaciente=" + generoPaciente + ", direccionPaciente=" + direccionPaciente + ", imgPaciente="
				+ imgPaciente + ", token=" + token + ", expiracionToken=" + expiracionToken + ", password=" + password
				+ ", password2=" + password2 + "]";
	}
}
