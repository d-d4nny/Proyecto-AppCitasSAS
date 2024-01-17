package appCitas.AppCitasSAS.dao;

import java.util.Calendar;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pacientes", schema = "sch_sas")
public class Paciente {

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_paciente", nullable = false)
	private long idPaciente;
	
	@Column(name = "nombre_completo_paciente", nullable = false, length = 50)
	private String nombreCompletoPaciente;
	
	@Column(name = "dni_paciente", nullable = false, unique = true, length = 9)
	private String dniPaciente;
	
	@Column(name = "tlf_paciente", nullable = true, length = 9)
	private String tlfPaciente;
	
	@Column(name = "email_paciente", nullable = false, unique = true, length = 50)
	private String emailPaciente;
	
	@Column(name = "contraseña_paciente", nullable = false, length = 100)
	private String contrasenaPaciente;
	
	@Column(name = "fch_nacimiento_paciente", nullable = true, updatable = false)
	private Calendar fchNacimientoPaciente;
	
	@Column(name = "genero_paciente", nullable = true, length = 1)
	private String generoPaciente;
	
	@Column(name = "direccion_paciente", nullable = true, length = 100)
	private String direccionPaciente;
	
	@Column(name = "img_paciente", nullable = true, length = 100)
	private String imgPaciente;
	
	@Column(name = "rol_paciente", nullable = false, length = 12)
	private String rolPaciente;

	@Column(name = "token_recuperacion", nullable = true, length = 100)
	private String token;

	@Column(name = "expiracion_token", nullable = true, length = 100)
	private Calendar expiracionToken;
	
	
	
	// GETTER / SETTER

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


	public Calendar getFchNacimientoPaciente() {
		return fchNacimientoPaciente;
	}


	public void setFchNacimientoPaciente(Calendar fchNacimientoPaciente) {
		this.fchNacimientoPaciente = fchNacimientoPaciente;
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


	public String getRolPaciente() {
		return rolPaciente;
	}


	public void setRolPaciente(String rolPaciente) {
		this.rolPaciente = rolPaciente;
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


	
	// CONSTRUCTORES
	
	public Paciente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Paciente(String nombreCompletoPaciente, String dniPaciente, String tlfPaciente, String emailPaciente,
			String contrasenaPaciente, Calendar fchNacimientoPaciente, String generoPaciente,
			String direccionPaciente) {
		super();
		this.nombreCompletoPaciente = nombreCompletoPaciente;
		this.dniPaciente = dniPaciente;
		this.tlfPaciente = tlfPaciente;
		this.emailPaciente = emailPaciente;
		this.contrasenaPaciente = contrasenaPaciente;
		this.fchNacimientoPaciente = fchNacimientoPaciente;
		this.generoPaciente = generoPaciente;
		this.direccionPaciente = direccionPaciente;
	}
	

	public Paciente(long idPaciente, String nombreCompletoPaciente, String dniPaciente, String tlfPaciente,
			String emailPaciente, String contrasenaPaciente, Calendar fchNacimientoPaciente, String generoPaciente,
			String direccionPaciente, String imgPaciente, String rolPaciente, String token, Calendar expiracionToken) {
		super();
		this.idPaciente = idPaciente;
		this.nombreCompletoPaciente = nombreCompletoPaciente;
		this.dniPaciente = dniPaciente;
		this.tlfPaciente = tlfPaciente;
		this.emailPaciente = emailPaciente;
		this.contrasenaPaciente = contrasenaPaciente;
		this.fchNacimientoPaciente = fchNacimientoPaciente;
		this.generoPaciente = generoPaciente;
		this.direccionPaciente = direccionPaciente;
		this.imgPaciente = imgPaciente;
		this.rolPaciente = rolPaciente;
		this.token = token;
		this.expiracionToken = expiracionToken;
	}


	
	// METODOS
	
	@Override
	public int hashCode() {
		return Objects.hash(contrasenaPaciente, direccionPaciente, dniPaciente, emailPaciente, expiracionToken,
				fchNacimientoPaciente, generoPaciente, idPaciente, imgPaciente, nombreCompletoPaciente, rolPaciente,
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
		Paciente other = (Paciente) obj;
		return Objects.equals(contrasenaPaciente, other.contrasenaPaciente)
				&& Objects.equals(direccionPaciente, other.direccionPaciente)
				&& Objects.equals(dniPaciente, other.dniPaciente) && Objects.equals(emailPaciente, other.emailPaciente)
				&& Objects.equals(expiracionToken, other.expiracionToken)
				&& Objects.equals(fchNacimientoPaciente, other.fchNacimientoPaciente)
				&& Objects.equals(generoPaciente, other.generoPaciente) && idPaciente == other.idPaciente
				&& Objects.equals(imgPaciente, other.imgPaciente)
				&& Objects.equals(nombreCompletoPaciente, other.nombreCompletoPaciente)
				&& rolPaciente == other.rolPaciente && Objects.equals(tlfPaciente, other.tlfPaciente)
				&& Objects.equals(token, other.token);
	}	

	
	@Override
	public String toString() {
		return "Paciente [idPaciente=" + idPaciente + ", nombreCompletoPaciente=" + nombreCompletoPaciente
				+ ", dniPaciente=" + dniPaciente + ", tlfPaciente=" + tlfPaciente + ", emailPaciente=" + emailPaciente
				+ ", contrasenaPaciente=" + contrasenaPaciente + ", fchNacimientoPaciente=" + fchNacimientoPaciente
				+ ", generoPaciente=" + generoPaciente + ", direccionPaciente=" + direccionPaciente + ", imgPaciente="
				+ imgPaciente + ", rolPaciente=" + rolPaciente + ", token=" + token + ", expiracionToken="
				+ expiracionToken + "]";
	}
	
}
