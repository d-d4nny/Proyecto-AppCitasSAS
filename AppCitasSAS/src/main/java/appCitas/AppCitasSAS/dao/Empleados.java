package appCitas.AppCitasSAS.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleados", schema = "sch_sas")
public class Empleados {

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado", nullable = false)
	private long idEmpleado;
	
	@Column(name = "nombre_completo_empleado", nullable = false, length = 50)
	private String nombreCompletoEmpleado;
	
	@Column(name = "identificador_empleado", nullable = false, unique = true, length = 9)
	private int identificadorEmpleado;
	
	@Column(name = "contraseña_empleado", nullable = false, length = 100)
	private String contrasenaEmpleado;
	
	@ManyToOne
    @JoinColumn(name="id_rol")
    Roles rol;
	
	@ManyToOne
    @JoinColumn(name="id_consulta_turno")
    ConsultaTurno consultaTurno;
}
