package appCitas.AppCitasSAS.dao;

import java.util.List;

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
}
