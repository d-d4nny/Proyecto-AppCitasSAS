package appCitas.AppCitasSASv2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;

public interface ConsultaTurnoRepositorio extends JpaRepository<ConsultaTurno, Long>{

	
	public ConsultaTurno findFirstByIdConsultaTurno(long idConsultaTurno);
	
	
	public boolean existsByIdConsultaTurno(long idConsultaTurno);
}
