package appCitas.AppCitasSASv2.dao;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultas_turnos", schema = "sch_sas")
public class ConsultaTurno {
	
	
	
	// ATRIBUTOS
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_consulta_turno", nullable = false)
	private long idConsultaTurno;
	
	@Column(name = "num_consulta", nullable = false, unique = true)
	private int numConsulta;
	
	@Column(name = "tramo_hora_turno_inicio", nullable = true)
	private Time tramoHoraTurnoInicio;
	
	@Column(name = "tramo_hora_turno_fin", nullable = true)
	private Time tramoHoraTurnoFin;
	
	@OneToMany(mappedBy="consultaTurno")
    List<Doctores> doctoresConConsultaTurno;

	
	
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

	public List<Doctores> getDoctoresConConsultaTurno() {
		return doctoresConConsultaTurno;
	}

	public void setDoctoresConConsultaTurno(List<Doctores> doctoresConConsultaTurno) {
		this.doctoresConConsultaTurno = doctoresConConsultaTurno;
	}

		
		
	// CONSTRUCTORES
	
	
	public ConsultaTurno() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConsultaTurno(int numConsulta, Time tramoHoraTurnoInicio, Time tramoHoraTurnoFin) {
		super();
		this.numConsulta = numConsulta;
		this.tramoHoraTurnoInicio = tramoHoraTurnoInicio;
		this.tramoHoraTurnoFin = tramoHoraTurnoFin;
	}

	public ConsultaTurno(long idConsultaTurno, int numConsulta, Time tramoHoraTurnoInicio,
			Time tramoHoraTurnoFin, List<Doctores> doctoresConConsultaTurno) {
		super();
		this.idConsultaTurno = idConsultaTurno;
		this.numConsulta = numConsulta;
		this.tramoHoraTurnoInicio = tramoHoraTurnoInicio;
		this.tramoHoraTurnoFin = tramoHoraTurnoFin;
		this.doctoresConConsultaTurno = doctoresConConsultaTurno;
	}

	
	
	// METODOS
	
	
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
		ConsultaTurno other = (ConsultaTurno) obj;
		return Objects.equals(doctoresConConsultaTurno, other.doctoresConConsultaTurno)
				&& idConsultaTurno == other.idConsultaTurno && numConsulta == other.numConsulta
				&& Objects.equals(tramoHoraTurnoFin, other.tramoHoraTurnoFin)
				&& Objects.equals(tramoHoraTurnoInicio, other.tramoHoraTurnoInicio);
	}

	@Override
	public String toString() {
		return "ConsultaTurno [idConsultaTurno=" + idConsultaTurno + ", numConsulta=" + numConsulta
				+ ", tramoHoraTurnoInicio=" + tramoHoraTurnoInicio + ", tramoHoraTurnoFin=" + tramoHoraTurnoFin
				+ ", doctoresConConsultaTurno=" + doctoresConConsultaTurno + "]";
	}
}
