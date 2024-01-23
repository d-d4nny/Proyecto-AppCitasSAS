package appCitas.AppCitasSAS.dao;

import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "citas", schema = "sch_sas")
public class Citas {
	
	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cita", nullable = false)
	private long idCita;
	
	@Column(name = "fecha_cita", nullable = false)
	private Calendar fechaCita;
	
	@Column(name = "motivo_cita", nullable = false)
	private String motivoCita;
	
	@Column(name = "estado_cita", nullable = false)
	private String estadoCita;
	
	@ManyToOne
    @JoinColumn(name="id_paciente")
    Paciente paciente;
	
	@ManyToOne
    @JoinColumn(name="id_empleado")
    Empleados empleado;
	
}
