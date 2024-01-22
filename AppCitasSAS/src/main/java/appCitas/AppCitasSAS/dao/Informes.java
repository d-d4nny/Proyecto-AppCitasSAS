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
}
