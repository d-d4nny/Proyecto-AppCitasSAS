package appCitas.AppCitasSASv2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dao.Doctores;

public interface DoctorRepositorio extends JpaRepository<Doctores, Long> {
	
	
	public Doctores findFirstByNombreCompletoDoctor(String NombreCompletoDoctor);
	
	
	public boolean existsByIdDoctor(long idDoctor);

}
