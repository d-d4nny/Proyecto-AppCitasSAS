package appCitas.AppCitasSASv2.repositorios;

import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;

public interface ConsultaTurnoRepositorio extends JpaRepository<ConsultaTurno, Long>{

	
	public ConsultaTurno findFirstByTramoHoraTurno(Calendar fchConsultaTurno);
	
	
	public boolean existsByIdConsultaTurno(long idConsultaTurno);
}
