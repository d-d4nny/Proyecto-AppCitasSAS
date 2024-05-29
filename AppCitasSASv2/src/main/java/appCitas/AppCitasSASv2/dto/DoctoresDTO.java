package appCitas.AppCitasSASv2.dto;

import java.util.List;
import java.util.Objects;

public class DoctoresDTO {
	
	
	// ATRIBUTOS
	
	private long idDoctor;
	private String nombreCompletoDoctor;
	private String especialidadDoctor;
	private List<ConsultaTurnoDTO> consultaTurnoConDoctor;
	
	
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
	public List<ConsultaTurnoDTO> getConsultaTurnoConDoctor() {
		return consultaTurnoConDoctor;
	}
	public void setConsultaTurnoConDoctor(List<ConsultaTurnoDTO> consultaTurnoConDoctor) {
		this.consultaTurnoConDoctor = consultaTurnoConDoctor;
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
			List<ConsultaTurnoDTO> consultaTurnoConDoctor) {
		super();
		this.idDoctor = idDoctor;
		this.nombreCompletoDoctor = nombreCompletoDoctor;
		this.especialidadDoctor = especialidadDoctor;
		this.consultaTurnoConDoctor = consultaTurnoConDoctor;
	}
	
	
	// CONSTRUCTORES
	
	@Override
	public int hashCode() {
		return Objects.hash(consultaTurnoConDoctor, especialidadDoctor, idDoctor, nombreCompletoDoctor);
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
		return Objects.equals(consultaTurnoConDoctor, other.consultaTurnoConDoctor)
				&& Objects.equals(especialidadDoctor, other.especialidadDoctor) && idDoctor == other.idDoctor
				&& Objects.equals(nombreCompletoDoctor, other.nombreCompletoDoctor);
	}
	@Override
	public String toString() {
		return "DoctoresDTO [idDoctor=" + idDoctor + ", nombreCompletoDoctor=" + nombreCompletoDoctor
				+ ", especialidadDoctor=" + especialidadDoctor + ", consultaTurnoConDoctor=" + consultaTurnoConDoctor
				+ "]";
	}
}
