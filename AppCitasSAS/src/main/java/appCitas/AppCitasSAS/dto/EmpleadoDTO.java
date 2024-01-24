
package appCitas.AppCitasSAS.dto;

import java.util.Calendar;
import java.util.Objects;

import appCitas.AppCitasSAS.dao.ConsultaTurno;

public class EmpleadoDTO {

	//ATRIBUTOS
	private long idEmpleado;
	private String nombreCompletoEmpleado;
	private String identificadorEmpleado;
	private String emailEmpleado;
	private String contrasenaEmpleado;
	private String token;
	private Calendar expiracionToken;
	private String rolPaciente;
	private ConsultaTurno consultaTurno;
	private String password;
	private String password2;
	
	

	// GETTER / SETTER
	
	
	public long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getNombreCompletoEmpleado() {
		return nombreCompletoEmpleado;
	}
	public void setNombreCompletoEmpleado(String nombreCompletoEmpleado) {
		this.nombreCompletoEmpleado = nombreCompletoEmpleado;
	}
	public String getIdentificadorEmpleado() {
		return identificadorEmpleado;
	}
	public void setIdentificadorEmpleado(String identificadorEmpleado) {
		this.identificadorEmpleado = identificadorEmpleado;
	}
	public String getEmailEmpleado() {
		return emailEmpleado;
	}
	public void setEmailEmpleado(String emailEmpleado) {
		this.emailEmpleado = emailEmpleado;
	}
	public String getContrasenaEmpleado() {
		return contrasenaEmpleado;
	}
	public void setContrasenaEmpleado(String contrasenaEmpleado) {
		this.contrasenaEmpleado = contrasenaEmpleado;
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
	public String getRolPaciente() {
		return rolPaciente;
	}
	public void setRolPaciente(String rolPaciente) {
		this.rolPaciente = rolPaciente;
	}
	public ConsultaTurno getConsultaTurno() {
		return consultaTurno;
	}
	public void setConsultaTurno(ConsultaTurno consultaTurno) {
		this.consultaTurno = consultaTurno;
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
	
	
	public EmpleadoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	
	public EmpleadoDTO(String nombreCompletoEmpleado, String identificadorEmpleado, String emailEmpleado, 
			String contrasenaEmpleado) {
		super();
		this.nombreCompletoEmpleado = nombreCompletoEmpleado;
		this.identificadorEmpleado = identificadorEmpleado;
		this.emailEmpleado = emailEmpleado;
		this.contrasenaEmpleado = contrasenaEmpleado;
	}
	
	
	public EmpleadoDTO(long idEmpleado, String nombreCompletoEmpleado, String identificadorEmpleado,
			String emailEmpleado, String contrasenaEmpleado, String token, Calendar expiracionToken, 
			String rolPaciente, ConsultaTurno consultaTurno, String password, String password2) {
		super();
		this.idEmpleado = idEmpleado;
		this.nombreCompletoEmpleado = nombreCompletoEmpleado;
		this.identificadorEmpleado = identificadorEmpleado;
		this.emailEmpleado = emailEmpleado;
		this.contrasenaEmpleado = contrasenaEmpleado;
		this.token = token;
		this.expiracionToken = expiracionToken;
		this.rolPaciente = rolPaciente;
		this.consultaTurno = consultaTurno;
		this.password = password;
		this.password2 = password2;
	}
	
	
	
	//METODOS
	
	
	@Override
	public int hashCode() {
		return Objects.hash(consultaTurno, contrasenaEmpleado, emailEmpleado, expiracionToken, idEmpleado,
				identificadorEmpleado, nombreCompletoEmpleado, password, password2, rolPaciente, token);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpleadoDTO other = (EmpleadoDTO) obj;
		return Objects.equals(consultaTurno, other.consultaTurno)
				&& Objects.equals(contrasenaEmpleado, other.contrasenaEmpleado)
				&& Objects.equals(emailEmpleado, other.emailEmpleado)
				&& Objects.equals(expiracionToken, other.expiracionToken) && idEmpleado == other.idEmpleado
				&& Objects.equals(identificadorEmpleado, other.identificadorEmpleado)
				&& Objects.equals(nombreCompletoEmpleado, other.nombreCompletoEmpleado)
				&& Objects.equals(password, other.password) && Objects.equals(password2, other.password2)
				&& Objects.equals(rolPaciente, other.rolPaciente) && Objects.equals(token, other.token);
	}
	
	
	@Override
	public String toString() {
		return "EmpleadoDTO [idEmpleado=" + idEmpleado + ", nombreCompletoEmpleado=" + nombreCompletoEmpleado
				+ ", identificadorEmpleado=" + identificadorEmpleado + ", emailEmpleado=" + emailEmpleado
				+ ", contrasenaEmpleado=" + contrasenaEmpleado + ", token=" + token + ", expiracionToken="
				+ expiracionToken + ", rolPaciente=" + rolPaciente + ", consultaTurno=" + consultaTurno + ", password="
				+ password + ", password2=" + password2 + "]";
	}
}
