package appCitas.AppCitasSASv2.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dao.Paciente;

public class CitasDTO {

	//ATRIBUTOS
	private long idCita;
	private Date fechaCita;
	private Time horaCita;
	private String motivoCita;
	private String estadoCita;
	private Paciente paciente;
	private ConsultaTurno consultaTurno;
	
	
	
	//GETTER / SETTER
	
	
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
	
	
	//CONSTRUCTORES
	
	public CitasDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public CitasDTO(Date fechaCita, Time horaCita, String motivoCita, String estadoCita) {
		super();
		this.fechaCita = fechaCita;
		this.horaCita = horaCita;
		this.motivoCita = motivoCita;
		this.estadoCita = estadoCita;
	}
	
	
	public CitasDTO(long idCita, Date fechaCita, Time horaCita, String motivoCita, String estadoCita, Paciente paciente,
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
	
	
	//METODOS
	
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
		CitasDTO other = (CitasDTO) obj;
		return Objects.equals(consultaTurno, other.consultaTurno) && Objects.equals(estadoCita, other.estadoCita)
				&& Objects.equals(fechaCita, other.fechaCita) && Objects.equals(horaCita, other.horaCita)
				&& idCita == other.idCita && Objects.equals(motivoCita, other.motivoCita)
				&& Objects.equals(paciente, other.paciente);
	}
	@Override
	public String toString() {
		return "CitasDTO [idCita=" + idCita + ", fechaCita=" + fechaCita + ", horaCita=" + horaCita + ", motivoCita="
				+ motivoCita + ", estadoCita=" + estadoCita + ", paciente=" + paciente + ", consultaTurno="
				+ consultaTurno + "]";
	}
	
}
