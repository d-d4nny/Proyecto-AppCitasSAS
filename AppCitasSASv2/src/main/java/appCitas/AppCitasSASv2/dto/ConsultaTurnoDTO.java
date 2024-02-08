package appCitas.AppCitasSASv2.dto;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class ConsultaTurnoDTO {
	
	
	
	// ATRIBUTOS
	
	
	private long idConsultaTurno;
	private int numConsulta;
	private Time tramoHoraTurnoInicio;
	private Time tramoHoraTurnoFin;
	private List<DoctoresDTO> doctoresConConsultaTurno;
	
	
	
	// GETTER / SETTER
	
	
	public long getIdConsultaTurno() {
		return idConsultaTurno;
	}
	public void setIdConsultaTurno(long idConsultaTurno) {
		this.idConsultaTurno = idConsultaTurno;
	}
	public int getNumConsulta() {
		return numConsulta;
	}
	public void setNumConsulta(int numConsulta) {
		this.numConsulta = numConsulta;
	}
	public Time getTramoHoraTurnoInicio() {
		return tramoHoraTurnoInicio;
	}
	public void setTramoHoraTurnoInicio(Time tramoHoraTurnoInicio) {
		this.tramoHoraTurnoInicio = tramoHoraTurnoInicio;
	}
	public Time getTramoHoraTurnoFin() {
		return tramoHoraTurnoFin;
	}
	public void setTramoHoraTurnoFin(Time tramoHoraTurnoFin) {
		this.tramoHoraTurnoFin = tramoHoraTurnoFin;
	}
	public List<DoctoresDTO> getDoctoresConConsultaTurno() {
		return doctoresConConsultaTurno;
	}
	public void setDoctoresConConsultaTurno(List<DoctoresDTO> doctoresConConsultaTurno) {
		this.doctoresConConsultaTurno = doctoresConConsultaTurno;
	}
	
	
	
	// METODOS
	
	
	public ConsultaTurnoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ConsultaTurnoDTO(int numConsulta, Time tramoHoraTurnoInicio, Time tramoHoraTurnoFin) {
		super();
		this.numConsulta = numConsulta;
		this.tramoHoraTurnoInicio = tramoHoraTurnoInicio;
		this.tramoHoraTurnoFin = tramoHoraTurnoFin;
	}
	
	public ConsultaTurnoDTO(long idConsultaTurno, int numConsulta, Time tramoHoraTurnoInicio,
			Time tramoHoraTurnoFin, List<DoctoresDTO> doctoresConConsultaTurno) {
		super();
		this.idConsultaTurno = idConsultaTurno;
		this.numConsulta = numConsulta;
		this.tramoHoraTurnoInicio = tramoHoraTurnoInicio;
		this.tramoHoraTurnoFin = tramoHoraTurnoFin;
		this.doctoresConConsultaTurno = doctoresConConsultaTurno;
	}
	
	
	
	//CONSTRUCTORES

	
	@Override
	public int hashCode() {
		return Objects.hash(doctoresConConsultaTurno, idConsultaTurno, numConsulta, tramoHoraTurnoFin,
				tramoHoraTurnoInicio);
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
		return Objects.equals(doctoresConConsultaTurno, other.doctoresConConsultaTurno)
				&& idConsultaTurno == other.idConsultaTurno && numConsulta == other.numConsulta
				&& Objects.equals(tramoHoraTurnoFin, other.tramoHoraTurnoFin)
				&& Objects.equals(tramoHoraTurnoInicio, other.tramoHoraTurnoInicio);
	}
	@Override
	public String toString() {
		return "ConsultaTurnoDTO [idConsultaTurno=" + idConsultaTurno + ", numConsulta=" + numConsulta
				+ ", tramoHoraTurnoInicio=" + tramoHoraTurnoInicio + ", tramoHoraTurnoFin=" + tramoHoraTurnoFin
				+ ", doctoresConConsultaTurno=" + doctoresConConsultaTurno + "]";
	}
}

