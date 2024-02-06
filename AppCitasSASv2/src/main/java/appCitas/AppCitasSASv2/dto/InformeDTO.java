package appCitas.AppCitasSASv2.dto;

import java.util.Calendar;
import java.util.Objects;

import appCitas.AppCitasSASv2.dao.Paciente;

public class InformeDTO {

	
	private long idInforme;
	private String nombreInforme;
	private String descInforme;
	private Calendar fchInforme;
	private Paciente paciente;
	
	
	
	// GETTER / SETTER
	
	
	public long getIdInforme() {
		return idInforme;
	}
	public void setIdInforme(long idInforme) {
		this.idInforme = idInforme;
	}
	public String getNombreInforme() {
		return nombreInforme;
	}
	public void setNombreInforme(String nombreInforme) {
		this.nombreInforme = nombreInforme;
	}
	public String getDescInforme() {
		return descInforme;
	}
	public void setDescInforme(String descInforme) {
		this.descInforme = descInforme;
	}
	public Calendar getFchInforme() {
		return fchInforme;
	}
	public void setFchInforme(Calendar fchInforme) {
		this.fchInforme = fchInforme;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	
	
	// METODOS
	
	
	public InformeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public InformeDTO(String nombreInforme, String descInforme, Calendar fchInforme) {
		super();
		this.nombreInforme = nombreInforme;
		this.descInforme = descInforme;
		this.fchInforme = fchInforme;
	}
	
	public InformeDTO(long idInforme, String nombreInforme, String descInforme, Calendar fchInforme,
			Paciente paciente) {
		super();
		this.idInforme = idInforme;
		this.nombreInforme = nombreInforme;
		this.descInforme = descInforme;
		this.fchInforme = fchInforme;
		this.paciente = paciente;
	}

	
	
	//CONSTRUCTORES
	
	
	@Override
	public int hashCode() {
		return Objects.hash(descInforme, fchInforme, idInforme, nombreInforme, paciente);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformeDTO other = (InformeDTO) obj;
		return Objects.equals(descInforme, other.descInforme) && Objects.equals(fchInforme, other.fchInforme)
				&& idInforme == other.idInforme && Objects.equals(nombreInforme, other.nombreInforme)
				&& Objects.equals(paciente, other.paciente);
	}
	@Override
	public String toString() {
		return "InformeDTO [idInforme=" + idInforme + ", nombreInforme=" + nombreInforme + ", descInforme="
				+ descInforme + ", fchInforme=" + fchInforme + ", paciente=" + paciente + "]";
	}
}
