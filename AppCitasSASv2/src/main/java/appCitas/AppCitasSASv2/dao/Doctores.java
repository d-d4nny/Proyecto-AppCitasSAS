package appCitas.AppCitasSASv2.dao;

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
@Table(name = "doctores", schema = "sch_sas")
public class Doctores {

	
	
	// ATRIBUTOS
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_doctor", nullable = false)
	private long idDoctor;
	
	@Column(name = "nombre_completo_doctor", nullable = false, length = 50)
	private String nombreCompletoDoctor;
	
	@Column(name = "especialidad_doctor", nullable = false, length = 50)
	private String especialidadDoctor;
	
	@ManyToOne
    @JoinColumn(name="id_consulta_turno")
    private ConsultaTurno consultaTurno;
	
	

	// GETTER / SETTER
	
	
	public long getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(long idDoctor) {
		this.idDoctor = idDoctor;
	}

	public String getNombreCompletoDoctor() {
		return nombreCompletoDoctor;
	}

	public void setNombreCompletoDoctor(String nombreCompletoDoctor) {
		this.nombreCompletoDoctor = nombreCompletoDoctor;
	}

	public String getEspecialidadDoctor() {
		return especialidadDoctor;
	}

	public void setEspecialidadDoctor(String especialidadDoctor) {
		this.especialidadDoctor = especialidadDoctor;
	}

	public ConsultaTurno getConsultaTurno() {
		return consultaTurno;
	}

	public void setConsultaTurno(ConsultaTurno consultaTurno) {
		this.consultaTurno = consultaTurno;
	}
	
	

	// CONSTRUCTORES
	

	public Doctores() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Doctores(String nombreCompletoDoctor, String especialidadDoctor) {
		super();
		this.nombreCompletoDoctor = nombreCompletoDoctor;
		this.especialidadDoctor = especialidadDoctor;
	}
	
	public Doctores(long idDoctor, String nombreCompletoDoctor, String especialidadDoctor,
			ConsultaTurno consultaTurno) {
		super();
		this.idDoctor = idDoctor;
		this.nombreCompletoDoctor = nombreCompletoDoctor;
		this.especialidadDoctor = especialidadDoctor;
		this.consultaTurno = consultaTurno;
	}
	
	
	
	// METODOS


	@Override
	public int hashCode() {
		return Objects.hash(consultaTurno, especialidadDoctor, idDoctor, nombreCompletoDoctor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctores other = (Doctores) obj;
		return Objects.equals(consultaTurno, other.consultaTurno)
				&& Objects.equals(especialidadDoctor, other.especialidadDoctor) && idDoctor == other.idDoctor
				&& Objects.equals(nombreCompletoDoctor, other.nombreCompletoDoctor);
	}

	//@Override
	//public String toString() {
	//	return "Doctores [idDoctor=" + idDoctor + ", nombreCompletoDoctor=" + nombreCompletoDoctor
	//			+ ", especialidadDoctor=" + especialidadDoctor + ", consultaTurno=" + consultaTurno + "]";
	//}
}
