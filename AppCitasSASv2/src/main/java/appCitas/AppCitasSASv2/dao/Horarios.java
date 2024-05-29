package appCitas.AppCitasSASv2.dao;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "horarios", schema = "sch_sas")
public class Horarios {	
	
	
	// ATRIBUTOS
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_horario", nullable = false)
	private long idHorario;
	
	@Column(name = "dia_semana", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DayOfWeek diaSemana;
	
	@Column(name = "tramo_horario_inicio", nullable = true)
	private Time tramoHorarioInicio;
	
	@Column(name = "tramo_horario_fin", nullable = true)
	private Time tramoHorarioFin;
	
	@OneToMany(mappedBy="horario")
    List<ConsultaTurno> consultaTurnosConHorario; 

	
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
	
	public List<ConsultaTurno> getConsultaTurnosConHorario() {
		return consultaTurnosConHorario;
	}

	public void setConsultaTurnosConHorario(List<ConsultaTurno> consultaTurnosConHorario) {
		this.consultaTurnosConHorario = consultaTurnosConHorario;
	}
	
	
	// CONSTRUCTORES
	

	public Horarios() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Horarios(DayOfWeek diaSemana, Time tramoHorarioInicio, Time tramoHorarioFin) {
		super();
		this.diaSemana = diaSemana;
		this.tramoHorarioInicio = tramoHorarioInicio;
		this.tramoHorarioFin = tramoHorarioFin;
	}

	public Horarios(long idHorario, DayOfWeek diaSemana, Time tramoHorarioInicio, Time tramoHorarioFin,
			List<ConsultaTurno> consultaTurnosConHorario) {
		super();
		this.idHorario = idHorario;
		this.diaSemana = diaSemana;
		this.tramoHorarioInicio = tramoHorarioInicio;
		this.tramoHorarioFin = tramoHorarioFin;
		this.consultaTurnosConHorario = consultaTurnosConHorario;
	}

	
	// METODOS
	
	@Override
	public int hashCode() {
		return Objects.hash(consultaTurnosConHorario, diaSemana, idHorario, tramoHorarioFin, tramoHorarioInicio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Horarios other = (Horarios) obj;
		return Objects.equals(consultaTurnosConHorario, other.consultaTurnosConHorario) && diaSemana == other.diaSemana
				&& idHorario == other.idHorario && Objects.equals(tramoHorarioFin, other.tramoHorarioFin)
				&& Objects.equals(tramoHorarioInicio, other.tramoHorarioInicio);
	}
}
