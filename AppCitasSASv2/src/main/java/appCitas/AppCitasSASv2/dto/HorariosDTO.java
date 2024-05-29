package appCitas.AppCitasSASv2.dto;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;

public class HorariosDTO {

	
	// ATRIBUTOS
	
	private long idHorario;
	private DayOfWeek diaSemana;
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
	public DayOfWeek getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(DayOfWeek diaSemana) {
		this.diaSemana = diaSemana;
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
	public HorariosDTO(DayOfWeek diaSemana, Time tramoHorarioInicio, Time tramoHorarioFin) {
		super();
		this.diaSemana = diaSemana;
		this.tramoHorarioInicio = tramoHorarioInicio;
		this.tramoHorarioFin = tramoHorarioFin;
	}
	public HorariosDTO(long idHorario, DayOfWeek diaSemana, Time tramoHorarioInicio, Time tramoHorarioFin,
			List<ConsultaTurnoDTO> consultaTurnoConTurno) {
		super();
		this.idHorario = idHorario;
		this.diaSemana = diaSemana;
		this.tramoHorarioInicio = tramoHorarioInicio;
		this.tramoHorarioFin = tramoHorarioFin;
		this.consultaTurnoConTurno = consultaTurnoConTurno;
	}
	
	
	// CONSTRUCTORES
	
	@Override
	public int hashCode() {
		return Objects.hash(consultaTurnoConTurno, diaSemana, idHorario, tramoHorarioFin, tramoHorarioInicio);
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
		return Objects.equals(consultaTurnoConTurno, other.consultaTurnoConTurno) && diaSemana == other.diaSemana
				&& idHorario == other.idHorario && Objects.equals(tramoHorarioFin, other.tramoHorarioFin)
				&& Objects.equals(tramoHorarioInicio, other.tramoHorarioInicio);
	}
	@Override
	public String toString() {
		return "HorariosDTO [idHorario=" + idHorario + ", diaSemana=" + diaSemana + ", tramoHorarioInicio="
				+ tramoHorarioInicio + ", tramoHorarioFin=" + tramoHorarioFin + ", consultaTurnoConTurno="
				+ consultaTurnoConTurno + "]";
	}
	
}
