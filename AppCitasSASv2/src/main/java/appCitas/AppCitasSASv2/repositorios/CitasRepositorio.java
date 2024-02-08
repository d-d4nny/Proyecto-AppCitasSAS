package appCitas.AppCitasSASv2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Citas;

@Service
public interface CitasRepositorio extends JpaRepository<Citas, Long> {
	
	
	public Citas findFirstByIdCita(long idCita);
	
	
	public boolean existsByIdCita(long idCita);

}
