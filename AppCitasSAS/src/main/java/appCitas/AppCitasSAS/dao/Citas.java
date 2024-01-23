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
@Table(name = "citas", schema = "sch_sas")
public class Citas {
	
	
	
	// ATRIBUTOS
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cita", nullable = false)
	private long idCita;
	
	@Column(name = "fecha_cita", nullable = false)
	private Calendar fechaCita;
	
	@Column(name = "motivo_cita", nullable = false)
	private String motivoCita;
	
	@Column(name = "estado_cita", nullable = false)
	private String estadoCita;
	
	@ManyToOne
    @JoinColumn(name="id_paciente")
    Paciente paciente;
	
	@ManyToOne
    @JoinColumn(name="id_empleado")
    Empleados empleado;

	
	
	// GETTER / SETTER
	
	
	public long getIdCita() {
		return idCita;
	}

	public void setIdCita(long idCita) {
		this.idCita = idCita;
	}

	public Calendar getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(Calendar fechaCita) {
		this.fechaCita = fechaCita;
	}

	public String getMotivoCita() {
		return motivoCita;
	}

	public void setMotivoCita(String motivoCita) {
		this.motivoCita = motivoCita;
	}

	public String getEstadoCita() {
		return estadoCita;
	}

	public void setEstadoCita(String estadoCita) {
		this.estadoCita = estadoCita;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Empleados getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleados empleado) {
		this.empleado = empleado;
	}

	
	
	// CONSTRUCTORES
	
	
	public Citas() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Citas(Calendar fechaCita, String motivoCita, String estadoCita) {
		super();
		this.fechaCita = fechaCita;
		this.motivoCita = motivoCita;
		this.estadoCita = estadoCita;
	}

	
	public Citas(long idCita, Calendar fechaCita, String motivoCita, String estadoCita, Paciente paciente,
			Empleados empleado) {
		super();
		this.idCita = idCita;
		this.fechaCita = fechaCita;
		this.motivoCita = motivoCita;
		this.estadoCita = estadoCita;
		this.paciente = paciente;
		this.empleado = empleado;
	}

	
	
	// METODOS
	
	
	@Override
	public int hashCode() {
		return Objects.hash(empleado, estadoCita, fechaCita, idCita, motivoCita, paciente);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Citas other = (Citas) obj;
		return Objects.equals(empleado, other.empleado) && Objects.equals(estadoCita, other.estadoCita)
				&& Objects.equals(fechaCita, other.fechaCita) && idCita == other.idCita
				&& Objects.equals(motivoCita, other.motivoCita) && Objects.equals(paciente, other.paciente);
	}

	
	@Override
	public String toString() {
		return "Citas [idCita=" + idCita + ", fechaCita=" + fechaCita + ", motivoCita=" + motivoCita + ", estadoCita="
				+ estadoCita + ", paciente=" + paciente + ", empleado=" + empleado + "]";
	}
}
