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
@Table(name = "consultas_turnos", schema = "sch_sas")
public class ConsultaTurno {
	
	// ATRIBUTOS
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_consulta_turno", nullable = false)
		private long idConsultaTurno;
		
		@Column(name = "num_consulta", nullable = false, unique = true)
		private int numConsulta;
		
		@Column(name = "tramo_hora_turno", nullable = false)
		private String tramoHoraTurno;
		
		@OneToMany(mappedBy="consultaTurno")
	    List<Empleados> empleadosConConsultaTurno;   

}
