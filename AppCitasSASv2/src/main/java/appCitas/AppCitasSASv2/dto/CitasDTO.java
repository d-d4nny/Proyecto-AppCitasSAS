package appCitas.AppCitasSASv2.dto;

import java.util.Objects;

public class CitasDTO {

	//ATRIBUTOS
	private long idCita;
	private String fechaCita;
	private String motivoCita;
	private String estadoCita;
	private PacienteDTO paciente;
	private DoctoresDTO doctor;
	
	
	
	//GETTER / SETTER
	
	
	public long getIdCita() {
		return idCita;
	}
	public void setIdCita(long idCita) {
		this.idCita = idCita;
	}
	public String getFechaCita() {
		return fechaCita;
	}
	public void setFechaCita(String fechaCita) {
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
	public PacienteDTO getPaciente() {
		return paciente;
	}
	public void setPaciente(PacienteDTO paciente) {
		this.paciente = paciente;
	}
	public DoctoresDTO getDoctor() {
		return doctor;
	}
	public void setDoctor(DoctoresDTO doctor) {
		this.doctor = doctor;
	}
	
	
	
	//CONSTRUCTORES
	
	
	public CitasDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public CitasDTO(String fechaCita, String motivoCita, String estadoCita) {
		super();
		this.fechaCita = fechaCita;
		this.motivoCita = motivoCita;
		this.estadoCita = estadoCita;
	}
	
	
	public CitasDTO(long idCita, String fechaCita, String motivoCita, String estadoCita, PacienteDTO paciente,
			DoctoresDTO doctor) {
		super();
		this.idCita = idCita;
		this.fechaCita = fechaCita;
		this.motivoCita = motivoCita;
		this.estadoCita = estadoCita;
		this.paciente = paciente;
		this.doctor = doctor;
	}
	
	
	
	//METODOS
	
	
	@Override
	public int hashCode() {
		return Objects.hash(doctor, estadoCita, fechaCita, idCita, motivoCita, paciente);
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
		return Objects.equals(doctor, other.doctor) && Objects.equals(estadoCita, other.estadoCita)
				&& Objects.equals(fechaCita, other.fechaCita) && idCita == other.idCita
				&& Objects.equals(motivoCita, other.motivoCita) && Objects.equals(paciente, other.paciente);
	}
	@Override
	public String toString() {
		return "CitasDTO [idCita=" + idCita + ", fechaCita=" + fechaCita + ", motivoCita=" + motivoCita
				+ ", estadoCita=" + estadoCita + ", paciente=" + paciente + ", doctor=" + doctor + "]";
	}
}
