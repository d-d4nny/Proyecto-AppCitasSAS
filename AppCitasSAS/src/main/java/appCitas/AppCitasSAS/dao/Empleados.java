package appCitas.AppCitasSAS.dao;

import java.util.Calendar;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleados", schema = "sch_sas")
public class Empleados {

	
	
	// ATRIBUTOS
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado", nullable = false)
	private long idEmpleado;
	
	@Column(name = "nombre_completo_empleado", nullable = false, length = 50)
	private String nombreCompletoEmpleado;
	
	@Column(name = "identificador_empleado", nullable = false, unique = true, length = 9)
	private String identificadorEmpleado;
	
	@Column(name = "email_empleado", nullable = false, unique = true, length = 50)
	private String emailEmpleado;
	
	@Column(name = "contraseña_empleado", nullable = false, length = 100)
	private String contrasenaEmpleado;
	
	@Column(name = "rol_empleado", nullable = false, length = 12)
	private String rolEmpleado;
	
	@Column(name = "token_recuperacion", nullable = true, length = 100)
	private String token;

	@Column(name = "expiracion_token", nullable = true, length = 100)
	private Calendar expiracionToken;
	
	@ManyToOne
    @JoinColumn(name="id_consulta_turno")
    ConsultaTurno consultaTurno;
	
	

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
	
	public String getRolEmpleado() {
		return rolEmpleado;
	}

	public void setRolEmpleado(String rolEmpleado) {
		this.rolEmpleado = rolEmpleado;
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

	public ConsultaTurno getConsultaTurno() {
		return consultaTurno;
	}

	public void setConsultaTurno(ConsultaTurno consultaTurno) {
		this.consultaTurno = consultaTurno;
	}

	

	// CONSTRUCTORES
	

	public Empleados() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Empleados(String nombreCompletoEmpleado, String identificadorEmpleado, String emailEmpleado , String contrasenaEmpleado) {
		super();
		this.nombreCompletoEmpleado = nombreCompletoEmpleado;
		this.identificadorEmpleado = identificadorEmpleado;
		this.emailEmpleado = emailEmpleado;
		this.contrasenaEmpleado = contrasenaEmpleado;
	}
	
	
	public Empleados(long idEmpleado, String nombreCompletoEmpleado, String identificadorEmpleado,
			String emailEmpleado, String contrasenaEmpleado, String rolEmpleado, String token, 
			Calendar expiracionToken, ConsultaTurno consultaTurno) {
		super();
		this.idEmpleado = idEmpleado;
		this.nombreCompletoEmpleado = nombreCompletoEmpleado;
		this.identificadorEmpleado = identificadorEmpleado;
		this.emailEmpleado = emailEmpleado;
		this.contrasenaEmpleado = contrasenaEmpleado;
		this.rolEmpleado = rolEmpleado;
		this.token = token;
		this.expiracionToken = expiracionToken;
		this.consultaTurno = consultaTurno;
	}
	

	
	// METODOS


	@Override
	public int hashCode() {
		return Objects.hash(consultaTurno, contrasenaEmpleado, emailEmpleado, expiracionToken, idEmpleado,
				identificadorEmpleado, nombreCompletoEmpleado, rolEmpleado, token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleados other = (Empleados) obj;
		return Objects.equals(consultaTurno, other.consultaTurno)
				&& Objects.equals(contrasenaEmpleado, other.contrasenaEmpleado)
				&& Objects.equals(emailEmpleado, other.emailEmpleado)
				&& Objects.equals(expiracionToken, other.expiracionToken) && idEmpleado == other.idEmpleado
				&& Objects.equals(identificadorEmpleado, other.identificadorEmpleado)
				&& Objects.equals(nombreCompletoEmpleado, other.nombreCompletoEmpleado)
				&& Objects.equals(rolEmpleado, other.rolEmpleado) && Objects.equals(token, other.token);
	}

	@Override
	public String toString() {
		return "Empleados [idEmpleado=" + idEmpleado + ", nombreCompletoEmpleado=" + nombreCompletoEmpleado
				+ ", identificadorEmpleado=" + identificadorEmpleado + ", emailEmpleado=" + emailEmpleado
				+ ", contrasenaEmpleado=" + contrasenaEmpleado + ", rolEmpleado=" + rolEmpleado + ", token=" + token
				+ ", expiracionToken=" + expiracionToken + ", consultaTurno=" + consultaTurno + "]";
	}
}
