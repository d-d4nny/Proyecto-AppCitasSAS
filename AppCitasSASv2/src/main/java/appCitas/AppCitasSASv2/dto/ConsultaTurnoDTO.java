 package appCitas.AppCitasSASv2.dto;

import java.util.Objects;

import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dao.Horarios;

public class ConsultaTurnoDTO {
	
	
	
	// ATRIBUTOS
	
	
	private long idConsultaTurno;
	private String nombreConsulta;
	private int numConsulta;
	private Doctores doctor;
	private Horarios horario;
	
	
	
	// GETTER / SETTER
	
	
	public long getIdConsultaTurno() {
		return idConsultaTurno;
	}
	public void setIdConsultaTurno(long idConsultaTurno) {
		this.idConsultaTurno = idConsultaTurno;
	}
	public String getNombreConsulta() {
		return nombreConsulta;
	}
	public void setNombreConsulta(String nombreConsulta) {
		this.nombreConsulta = nombreConsulta;
	}
	public int getNumConsulta() {
		return numConsulta;
	}
	public void setNumConsulta(int numConsulta) {
		this.numConsulta = numConsulta;
	}
	public Doctores getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctores doctor) {
		this.doctor = doctor;
	}
	public Horarios getHorario() {
		return horario;
	}
	public void setHorario(Horarios horario) {
		this.horario = horario;
	}
	
	
	// METODOS
	
	public ConsultaTurnoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}	
	public ConsultaTurnoDTO(String nombreConsulta, int numConsulta) {
		super();
		this.nombreConsulta = nombreConsulta;
		this.numConsulta = numConsulta;
	}
	public ConsultaTurnoDTO(long idConsultaTurno, String nombreConsulta, int numConsulta, Doctores doctor,
			Horarios horario) {
		super();
		this.idConsultaTurno = idConsultaTurno;
		this.nombreConsulta = nombreConsulta;
		this.numConsulta = numConsulta;
		this.doctor = doctor;
		this.horario = horario;
	}
	
	
	//CONSTRUCTORES

	@Override
	public int hashCode() {
		return Objects.hash(doctor, horario, idConsultaTurno, nombreConsulta, numConsulta);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsultaTurnoDTO other = (ConsultaTurnoDTO) obj;
		return Objects.equals(doctor, other.doctor) && Objects.equals(horario, other.horario)
				&& idConsultaTurno == other.idConsultaTurno && Objects.equals(nombreConsulta, other.nombreConsulta)
				&& numConsulta == other.numConsulta;
	}
	@Override
	public String toString() {
		return "ConsultaTurnoDTO [idConsultaTurno=" + idConsultaTurno + ", nombreConsulta=" + nombreConsulta
				+ ", numConsulta=" + numConsulta + ", doctor=" + doctor + ", horario=" + horario + "]";
	}
}

