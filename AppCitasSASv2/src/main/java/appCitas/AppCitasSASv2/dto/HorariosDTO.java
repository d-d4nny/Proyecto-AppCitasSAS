package appCitas.AppCitasSASv2.dto;

import java.sql.Time;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import appCitas.AppCitasSASv2.dao.DiaSemana;

public class HorariosDTO {

	
	// ATRIBUTOS
	
	private long idHorario;
	private Set<DiaSemana> diasSemana;
	private Time tramoHorarioInicio;
	private Time tramoHorarioFin;
	private List<ConsultaTurnoDTO> consultaTurnoConTurno;
	
	
	// GETTER / SETTER
	
	public long getIdHorario() {
		return idHorario;
	}
	public void setIdHorario(long idHorario) {
		this.idHorario = idHorario;
	}
	public Set<DiaSemana> getDiasSemana() {
		return diasSemana;
	}
	public void setDiasSemana(Set<DiaSemana> diasSemana) {
		this.diasSemana = diasSemana;
	}
	public Time getTramoHorarioInicio() {
		return tramoHorarioInicio;
	}
	public void setTramoHorarioInicio(Time tramoHorarioInicio) {
		this.tramoHorarioInicio = tramoHorarioInicio;
	}
	public Time getTramoHorarioFin() {
		return tramoHorarioFin;
	}
	public void setTramoHorarioFin(Time tramoHorarioFin) {
		this.tramoHorarioFin = tramoHorarioFin;
	}
	public List<ConsultaTurnoDTO> getConsultaTurnoConTurno() {
		return consultaTurnoConTurno;
	}
	public void setConsultaTurnoConTurno(List<ConsultaTurnoDTO> consultaTurnoConTurno) {
		this.consultaTurnoConTurno = consultaTurnoConTurno;
	}
	
	
	// METODOS	
	
	public HorariosDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HorariosDTO(Set<DiaSemana> diasSemana, Time tramoHorarioInicio, Time tramoHorarioFin) {
		super();
		this.diasSemana = diasSemana;
		this.tramoHorarioInicio = tramoHorarioInicio;
		this.tramoHorarioFin = tramoHorarioFin;
	}
	public HorariosDTO(long idHorario, Set<DiaSemana> diasSemana, Time tramoHorarioInicio, Time tramoHorarioFin,
			List<ConsultaTurnoDTO> consultaTurnoConTurno) {
		super();
		this.idHorario = idHorario;
		this.diasSemana = diasSemana;
		this.tramoHorarioInicio = tramoHorarioInicio;
		this.tramoHorarioFin = tramoHorarioFin;
		this.consultaTurnoConTurno = consultaTurnoConTurno;
	}
	
	
	// CONSTRUCTORES
	
	@Override
	public int hashCode() {
		return Objects.hash(consultaTurnoConTurno, diasSemana, idHorario, tramoHorarioFin, tramoHorarioInicio);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HorariosDTO other = (HorariosDTO) obj;
		return Objects.equals(consultaTurnoConTurno, other.consultaTurnoConTurno)
				&& Objects.equals(diasSemana, other.diasSemana) && Objects.equals(idHorario, other.idHorario)
				&& Objects.equals(tramoHorarioFin, other.tramoHorarioFin)
				&& Objects.equals(tramoHorarioInicio, other.tramoHorarioInicio);
	}
	@Override
	public String toString() {
		return "HorariosDTO [idHorario=" + idHorario + ", diasSemana=" + diasSemana + ", tramoHorarioInicio="
				+ tramoHorarioInicio + ", tramoHorarioFin=" + tramoHorarioFin + ", consultaTurnoConTurno="
				+ consultaTurnoConTurno + "]";
	}
}
