package appCitas.AppCitasSASv2.dto;

import java.util.Objects;

public class DoctoresDTO {
	
	// ATRIBUTOS
	
	
	private long idDoctor;
	private String nombreCompletoDoctor;
	private String especialidadDoctor;
	private ConsultaTurnoDTO consultaTurno;
	
	
	
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
	public ConsultaTurnoDTO getConsultaTurno() {
		return consultaTurno;
	}
	public void setConsultaTurno(ConsultaTurnoDTO consultaTurno) {
		this.consultaTurno = consultaTurno;
	}
	
	
	
	// METODOS
	
	
	public DoctoresDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DoctoresDTO(String nombreCompletoDoctor, String especialidadDoctor) {
		super();
		this.nombreCompletoDoctor = nombreCompletoDoctor;
		this.especialidadDoctor = especialidadDoctor;
	}
	
	public DoctoresDTO(long idDoctor, String nombreCompletoDoctor, String especialidadDoctor,
			ConsultaTurnoDTO consultaTurno) {
		super();
		this.idDoctor = idDoctor;
		this.nombreCompletoDoctor = nombreCompletoDoctor;
		this.especialidadDoctor = especialidadDoctor;
		this.consultaTurno = consultaTurno;
	}
	
	
	
	// CONSTRUCTORES
	
	
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
		DoctoresDTO other = (DoctoresDTO) obj;
		return Objects.equals(consultaTurno, other.consultaTurno)
				&& Objects.equals(especialidadDoctor, other.especialidadDoctor) && idDoctor == other.idDoctor
				&& Objects.equals(nombreCompletoDoctor, other.nombreCompletoDoctor);
	}
	@Override
	public String toString() {
		return "DoctoresDTO [idDoctor=" + idDoctor + ", nombreCompletoDoctor=" + nombreCompletoDoctor
				+ ", especialidadDoctor=" + especialidadDoctor + ", consultaTurno=" + consultaTurno + "]";
	}
}
