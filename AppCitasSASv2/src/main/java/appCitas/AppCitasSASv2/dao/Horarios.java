package appCitas.AppCitasSASv2.dao;

import java.sql.Time;
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
	
	@ElementCollection(targetClass = DiaSemana.class)
	@Enumerated(EnumType.STRING)
	@Column(name = "dias_semana")
	private Set<DiaSemana> diasSemana;
	
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

	public Horarios(Set<DiaSemana> diasSemana, Time tramoHorarioInicio, Time tramoHorarioFin) {
		super();
		this.diasSemana = diasSemana;
		this.tramoHorarioInicio = tramoHorarioInicio;
		this.tramoHorarioFin = tramoHorarioFin;
	}

	public Horarios(long idHorario, Set<DiaSemana> diasSemana, Time tramoHorarioInicio, Time tramoHorarioFin,
			List<ConsultaTurno> consultaTurnosConHorario) {
		super();
		this.idHorario = idHorario;
		this.diasSemana = diasSemana;
		this.tramoHorarioInicio = tramoHorarioInicio;
		this.tramoHorarioFin = tramoHorarioFin;
		this.consultaTurnosConHorario = consultaTurnosConHorario;
	}

	
	// METODOS
	
	@Override
	public int hashCode() {
		return Objects.hash(consultaTurnosConHorario, diasSemana, idHorario, tramoHorarioFin, tramoHorarioInicio);
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
		return Objects.equals(consultaTurnosConHorario, other.consultaTurnosConHorario)
				&& Objects.equals(diasSemana, other.diasSemana) && idHorario == other.idHorario
				&& Objects.equals(tramoHorarioFin, other.tramoHorarioFin)
				&& Objects.equals(tramoHorarioInicio, other.tramoHorarioInicio);
	}
}
