package appCitas.AppCitasSASv2.dao;

import java.sql.Date;
import java.sql.Time;
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
	private Date fechaCita;
	
	@Column(name = "hora_cita", nullable = false)
	private Time horaCita;
	
	@Column(name = "motivo_cita", nullable = false)
	private String motivoCita;
	
	@Column(name = "estado_cita", nullable = false)
	private String estadoCita;
	
	@ManyToOne
    @JoinColumn(name="id_paciente")
    Paciente paciente;
	
	@ManyToOne
    @JoinColumn(name="id_consulta_turno")
    ConsultaTurno consultaTurno;
	
	
	// GETTER / SETTER
	
	
	public long getIdCita() {
		return idCita;
	}

	public void setIdCita(long idCita) {
		this.idCita = idCita;
	}

	public Date getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}

	public Time getHoraCita() {
		return horaCita;
	}

	public void setHoraCita(Time horaCita) {
		this.horaCita = horaCita;
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

	public ConsultaTurno getConsultaTurno() {
		return consultaTurno;
	}

	public void setConsultaTurno(ConsultaTurno consultaTurno) {
		this.consultaTurno = consultaTurno;
	}

	
	
	// CONSTRUCTORES
	
	
	public Citas() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Citas(Date fechaCita, Time horaCita, String motivoCita, String estadoCita) {
		super();
		this.fechaCita = fechaCita;
		this.horaCita = horaCita;
		this.motivoCita = motivoCita;
		this.estadoCita = estadoCita;
	}

	
	public Citas(long idCita, Date fechaCita, Time horaCita, String motivoCita, String estadoCita, Paciente paciente,
			ConsultaTurno consultaTurno) {
		super();
		this.idCita = idCita;
		this.fechaCita = fechaCita;
		this.horaCita = horaCita;
		this.motivoCita = motivoCita;
		this.estadoCita = estadoCita;
		this.paciente = paciente;
		this.consultaTurno = consultaTurno;
	}

	
	
	// METODOS
	
	
	@Override
	public int hashCode() {
		return Objects.hash(consultaTurno, estadoCita, fechaCita, horaCita, idCita, motivoCita, paciente);
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
		return Objects.equals(consultaTurno, other.consultaTurno) && Objects.equals(estadoCita, other.estadoCita)
				&& Objects.equals(fechaCita, other.fechaCita) && Objects.equals(horaCita, other.horaCita) && idCita == other.idCita 
				&& Objects.equals(motivoCita, other.motivoCita) && Objects.equals(paciente, other.paciente);
	}

	
	//@Override
	//public String toString() {
	//	return "Citas [idCita=" + idCita + ", fechaCita=" + fechaCita + ", horaCita=" + horaCita + ", motivoCita=" + motivoCita + ", estadoCita="
	//			+ estadoCita + ", paciente=" + paciente + ", doctor=" + doctor + "]";
	//}
}
