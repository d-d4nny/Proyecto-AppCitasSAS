package appCitas.AppCitasSAS.dto;

import java.util.Calendar;
import java.util.Objects;

import appCitas.AppCitasSAS.dao.ConsultaTurno;
import appCitas.AppCitasSAS.dao.Roles;

public class EmpleadoDTO {

	//ATRIBUTOS
	private long idEmpleado;
	private String nombreCompletoEmpleado;
	private int identificadorEmpleado;
	private String contrasenaEmpleado;
	private String token;
	private Calendar expiracionToken;
	private Roles rol;
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
	public int getIdentificadorEmpleado() {
		return identificadorEmpleado;
	}
	public void setIdentificadorEmpleado(int identificadorEmpleado) {
		this.identificadorEmpleado = identificadorEmpleado;
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
	public Roles getRol() {
		return rol;
	}
	public void setRol(Roles rol) {
		this.rol = rol;
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
	
	
	public EmpleadoDTO(String nombreCompletoEmpleado, int identificadorEmpleado, String contrasenaEmpleado,
			String token, Calendar expiracionToken) {
		super();
		this.nombreCompletoEmpleado = nombreCompletoEmpleado;
		this.identificadorEmpleado = identificadorEmpleado;
		this.contrasenaEmpleado = contrasenaEmpleado;
		this.token = token;
		this.expiracionToken = expiracionToken;
	}
	

	public EmpleadoDTO(long idEmpleado, String nombreCompletoEmpleado, int identificadorEmpleado,
			String contrasenaEmpleado, String token, Calendar expiracionToken, Roles rol, ConsultaTurno consultaTurno,
			String password, String password2) {
		super();
		this.idEmpleado = idEmpleado;
		this.nombreCompletoEmpleado = nombreCompletoEmpleado;
		this.identificadorEmpleado = identificadorEmpleado;
		this.contrasenaEmpleado = contrasenaEmpleado;
		this.token = token;
		this.expiracionToken = expiracionToken;
		this.rol = rol;
		this.consultaTurno = consultaTurno;
		this.password = password;
		this.password2 = password2;
	}
	
	
	
	//METODOS
	
	
	@Override
	public int hashCode() {
		return Objects.hash(consultaTurno, contrasenaEmpleado, expiracionToken, idEmpleado, identificadorEmpleado,
				nombreCompletoEmpleado, password, password2, rol, token);
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
				&& Objects.equals(expiracionToken, other.expiracionToken) && idEmpleado == other.idEmpleado
				&& identificadorEmpleado == other.identificadorEmpleado
				&& Objects.equals(nombreCompletoEmpleado, other.nombreCompletoEmpleado)
				&& Objects.equals(password, other.password) && Objects.equals(password2, other.password2)
				&& Objects.equals(rol, other.rol) && Objects.equals(token, other.token);
	}
	@Override
	public String toString() {
		return "EmpleadoDTO [idEmpleado=" + idEmpleado + ", nombreCompletoEmpleado=" + nombreCompletoEmpleado
				+ ", identificadorEmpleado=" + identificadorEmpleado + ", contrasenaEmpleado=" + contrasenaEmpleado
				+ ", token=" + token + ", expiracionToken=" + expiracionToken + ", rol=" + rol + ", consultaTurno="
				+ consultaTurno + ", password=" + password + ", password2=" + password2 + "]";
	}
}
