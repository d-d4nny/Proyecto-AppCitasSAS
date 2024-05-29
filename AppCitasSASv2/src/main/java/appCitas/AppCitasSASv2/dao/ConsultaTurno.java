package appCitas.AppCitasSASv2.dao;

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
@Table(name = "consultas_turnos", schema = "sch_sas")
public class ConsultaTurno {
	
	
	
	// ATRIBUTOS
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_consulta_turno", nullable = false)
	private long idConsultaTurno;
	
	@Column(name = "nombre_consulta", nullable = false, length = 50)
	private String nombreConsulta;
	
	@Column(name = "num_consulta", nullable = false)
	private int numConsulta;
	
	@ManyToOne
    @JoinColumn(name="id_doctor")
	private Doctores doctor;
	
	@ManyToOne
    @JoinColumn(name="id_horario")
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

		
		
	// CONSTRUCTORES
	

	public ConsultaTurno() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConsultaTurno(String nombreConsulta ,int numConsulta) {
		super();
		this.nombreConsulta = nombreConsulta;
		this.numConsulta = numConsulta;
	}

	public ConsultaTurno(long idConsultaTurno, String nombreConsulta ,int numConsulta, Time tramoHoraTurnoInicio,
			Time tramoHoraTurnoFin, Doctores doctor, Horarios horario) {
		super();
		this.idConsultaTurno = idConsultaTurno;
		this.nombreConsulta = nombreConsulta;
		this.numConsulta = numConsulta;
		this.doctor = doctor;
		this.horario = horario;
	}

	
	// METODOS
	
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
		ConsultaTurno other = (ConsultaTurno) obj;
		return Objects.equals(doctor, other.doctor) && Objects.equals(horario, other.horario)
				&& idConsultaTurno == other.idConsultaTurno && Objects.equals(nombreConsulta, other.nombreConsulta)
				&& numConsulta == other.numConsulta;
	}

	//@Override
	//public String toString() {
	//	return "ConsultaTurno [idConsultaTurno=" + idConsultaTurno + ", numConsulta=" + numConsulta
	//			+ ", tramoHoraTurnoInicio=" + tramoHoraTurnoInicio + ", tramoHoraTurnoFin=" + tramoHoraTurnoFin
	//			+ ", doctoresConConsultaTurno=" + doctoresConConsultaTurno + "]";
	//}
}
