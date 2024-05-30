package appCitas.AppCitasSASv2.dto;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import appCitas.AppCitasSASv2.dao.Citas;
import appCitas.AppCitasSASv2.dao.Informes;

public class PacienteDTO {

	//ATRIBUTOS
	private long idPaciente;
	private String nombreCompletoPaciente;
	private String dniPaciente;
	private String tlfPaciente;
	private String emailPaciente;
	private String contrasenaPaciente;
	private String generoPaciente;
	private String direccionPaciente;
	private String profilePicture;
	private String rolPaciente;
	private String token;
	private Calendar expiracionToken;
	private boolean cuentaConfirmada;
	private List<Informes> informesDePaciente = new ArrayList<>();
	private List<Citas> citasDePaciente = new ArrayList<>();
	private String mensajeError = "aaaaaa";
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


	public String getProfilePicture() {
        return profilePicture;
    }

	
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
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
	
	
	public boolean isCuentaConfirmada() {
		return cuentaConfirmada;
	}


	public void setCuentaConfirmada(boolean cuentaConfirmada) {
		this.cuentaConfirmada = cuentaConfirmada;
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

	
	public String getRolPaciente() {
		return rolPaciente;
	}


	public void setRolPaciente(String rolPaciente) {
		this.rolPaciente = rolPaciente;
	}


	public List<Informes> getInformesDePaciente() {
		return informesDePaciente;
	}


	public void setInformesDePaciente(List<Informes> informesDePaciente) {
		this.informesDePaciente = informesDePaciente;
	}


	public List<Citas> getCitasDePaciente() {
		return citasDePaciente;
	}


	public void setCitasDePaciente(List<Citas> citasDePaciente) {
		this.citasDePaciente = citasDePaciente;
	}
	
	
	public String getMensajeError() {
		return mensajeError;
	}


	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	
	
	
	//CONSTRUCTORES


	public PacienteDTO() {
		
	}
	

	public PacienteDTO(String nombreCompletoPaciente, String dniPaciente, String tlfPaciente,
			String emailPaciente, String contrasenaPaciente, String generoPaciente,
			String direccionPaciente) {
		super();
		this.nombreCompletoPaciente = nombreCompletoPaciente;
		this.dniPaciente = dniPaciente;
		this.tlfPaciente = tlfPaciente;
		this.emailPaciente = emailPaciente;
		this.contrasenaPaciente = contrasenaPaciente;
		this.generoPaciente = generoPaciente;
		this.direccionPaciente = direccionPaciente;
	}


	public PacienteDTO(long idPaciente, String nombreCompletoPaciente, String dniPaciente, String tlfPaciente,
			String emailPaciente, String contrasenaPaciente, String generoPaciente,
			String direccionPaciente, String profilePicture, String rolPaciente, String token, Calendar expiracionToken, boolean cuentaConfirmada,
			List<Informes> informesDePaciente, List<Citas> citasDePaciente, String password, String password2) {
		super();
		this.idPaciente = idPaciente;
		this.nombreCompletoPaciente = nombreCompletoPaciente;
		this.dniPaciente = dniPaciente;
		this.tlfPaciente = tlfPaciente;
		this.emailPaciente = emailPaciente;
		this.contrasenaPaciente = contrasenaPaciente;
		this.generoPaciente = generoPaciente;
		this.direccionPaciente = direccionPaciente;
		this.profilePicture = profilePicture;
		this.rolPaciente = rolPaciente;
		this.token = token;
		this.cuentaConfirmada = cuentaConfirmada;
		this.expiracionToken = expiracionToken;
		this.informesDePaciente = informesDePaciente;
		this.citasDePaciente = citasDePaciente;
		this.password = password;
		this.password2 = password2;
	}

	
	
	//METODOS


	@Override
	public int hashCode() {
		return Objects.hash(citasDePaciente, contrasenaPaciente, direccionPaciente, dniPaciente, emailPaciente,
				expiracionToken, generoPaciente, profilePicture, idPaciente, informesDePaciente, cuentaConfirmada,
				nombreCompletoPaciente, password, password2, rolPaciente, tlfPaciente, token);
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
		return Objects.equals(citasDePaciente, other.citasDePaciente)
				&& Objects.equals(contrasenaPaciente, other.contrasenaPaciente)
				&& Objects.equals(direccionPaciente, other.direccionPaciente)
				&& Objects.equals(dniPaciente, other.dniPaciente) && Objects.equals(emailPaciente, other.emailPaciente)
				&& Objects.equals(expiracionToken, other.expiracionToken)
				&& Objects.equals(generoPaciente, other.generoPaciente) && idPaciente == other.idPaciente
				&& Objects.equals(informesDePaciente, other.informesDePaciente)
				&& Objects.equals(profilePicture, other.profilePicture)
				&& Objects.equals(nombreCompletoPaciente, other.nombreCompletoPaciente)
				&& Objects.equals(cuentaConfirmada, other.cuentaConfirmada)
				&& Objects.equals(password, other.password) && Objects.equals(password2, other.password2)
				&& Objects.equals(rolPaciente, other.rolPaciente) && Objects.equals(tlfPaciente, other.tlfPaciente)
				&& Objects.equals(token, other.token);
	}


	@Override
	public String toString() {
		return "PacienteDTO [idPaciente=" + idPaciente + ", nombreCompletoPaciente=" + nombreCompletoPaciente
				+ ", dniPaciente=" + dniPaciente + ", tlfPaciente=" + tlfPaciente + ", emailPaciente=" + emailPaciente
				+ ", contrasenaPaciente=" + contrasenaPaciente + ", generoPaciente=" + generoPaciente
				+ ", direccionPaciente=" + direccionPaciente + ", profilePicture=" + profilePicture + ", rolPaciente="
				+ rolPaciente + ", token=" + token + ", expiracionToken=" + expiracionToken + ", cuentaConfirmada="
				+ cuentaConfirmada + ", informesDePaciente=" + informesDePaciente + ", citasDePaciente="
				+ citasDePaciente + ", mensajeError=" + mensajeError + ", password=" + password + ", password2="
				+ password2 + "]";
	}
}
