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
@Table(name = "informes", schema = "sch_sas")
public class Informes {
	
	
	
	// ATRIBUTOS
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_informe", nullable = false)
	private long idInforme;
	
	@Column(name = "nombre_informe", nullable = false, length = 50)
	private String nombreInforme;
	
	@ManyToOne
    @JoinColumn(name="id_paciente")
    Paciente paciente;
	
	

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

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	
	
	// CONSTRUCTORES
	
	
	public Informes() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Informes(String nombreInforme) {
		super();
		this.nombreInforme = nombreInforme;
	}

	
	public Informes(long idInforme, String nombreInforme, Paciente paciente) {
		super();
		this.idInforme = idInforme;
		this.nombreInforme = nombreInforme;
		this.paciente = paciente;
	}

	

	// CONSTRUCTORES
	
	
	@Override
	public int hashCode() {
		return Objects.hash(idInforme, nombreInforme, paciente);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Informes other = (Informes) obj;
		return idInforme == other.idInforme && Objects.equals(nombreInforme, other.nombreInforme)
				&& Objects.equals(paciente, other.paciente);
	}

	
	@Override
	public String toString() {
		return "Informes [idInforme=" + idInforme + ", nombreInforme=" + nombreInforme + ", paciente=" + paciente + "]";
	}
}
