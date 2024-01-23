package appCitas.AppCitasSAS.dao;

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
@Table(name = "roles", schema = "sch_sas")
public class Roles {

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol", nullable = false)
	private long idRol;
	
	@Column(name = "codigo_rol", nullable = false, unique = true)
	private String codigoRol;
	
	@Column(name = "desc_tipo_rol", nullable = false, length = 50)
	private String descTipoRol;
	
	@OneToMany(mappedBy="rol")
    List<Empleados> empleadosConRol;  
	
	
	
	// GETTER / SETTER
	
	
	public long getIdRol() {
		return idRol;
	}

	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}

	public String getCodigoRol() {
		return codigoRol;
	}

	public void setCodigoRol(String codigoRol) {
		this.codigoRol = codigoRol;
	}

	public String getDescTipoRol() {
		return descTipoRol;
	}

	public void setDescTipoRol(String descTipoRol) {
		this.descTipoRol = descTipoRol;
	}

	public List<Empleados> getEmpleadosConRol() {
		return empleadosConRol;
	}

	public void setEmpleadosConRol(List<Empleados> empleadosConRol) {
		this.empleadosConRol = empleadosConRol;
	}


	
	// CONSTRUCTORES
	
	
	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}   
	
	
	public Roles(String codigoRol, String descTipoRol) {
		super();
		this.codigoRol = codigoRol;
		this.descTipoRol = descTipoRol;
	}
	
	
	public Roles(long idRol, String codigoRol, String descTipoRol, List<Empleados> empleadosConRol) {
		super();
		this.idRol = idRol;
		this.codigoRol = codigoRol;
		this.descTipoRol = descTipoRol;
		this.empleadosConRol = empleadosConRol;
	}

	
	
	// METODOS
	
	
	@Override
	public int hashCode() {
		return Objects.hash(codigoRol, descTipoRol, empleadosConRol, idRol);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Roles other = (Roles) obj;
		return Objects.equals(codigoRol, other.codigoRol) && Objects.equals(descTipoRol, other.descTipoRol)
				&& Objects.equals(empleadosConRol, other.empleadosConRol) && idRol == other.idRol;
	}
	

	@Override
	public String toString() {
		return "Roles [idRol=" + idRol + ", codigoRol=" + codigoRol + ", descTipoRol=" + descTipoRol
				+ ", empleadosConRol=" + empleadosConRol + "]";
	}
}
