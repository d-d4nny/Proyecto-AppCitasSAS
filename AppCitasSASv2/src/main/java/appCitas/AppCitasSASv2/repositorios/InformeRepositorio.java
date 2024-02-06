package appCitas.AppCitasSASv2.repositorios;

import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;

import appCitas.AppCitasSASv2.dao.Informes;

public interface InformeRepositorio extends JpaRepository<Informes, Long> {

	
	public Informes findFirstByFchInforme(Calendar fchInforme);
	
	
	public boolean existsByIdInforme(long idInforme);
}
