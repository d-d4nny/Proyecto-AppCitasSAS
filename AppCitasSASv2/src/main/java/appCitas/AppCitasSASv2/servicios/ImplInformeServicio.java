package appCitas.AppCitasSASv2.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Informes;
import appCitas.AppCitasSASv2.dto.InformeDTO;
import appCitas.AppCitasSASv2.repositorios.InformeRepositorio;
import jakarta.persistence.PersistenceException;

@Service
public class ImplInformeServicio implements IntfInformeServicio{

	@Autowired
	private IntfInformeToDao toDao;
	
	@Autowired
	private IntfInformeToDto toDto;
	
	@Autowired
	private InformeRepositorio repositorio;
	
	
	@Override
	public Boolean crearInforme(InformeDTO informeDTO) {
		try {
			repositorio.save(toDao.informeToDao(informeDTO));
			return true;
		} catch (PersistenceException e) {
			System.out.println("\n[ERROR InformeServicioImpl - crearInforme()] - Al registrar nuevo informe: " + e);
			return false;
		}
	}
	
	
	@Override
	public Informes eliminar(long id) {
		Informes informe = repositorio.findById(id).orElse(null);
		if(informe != null) {
			repositorio.delete(informe);
		}
		return informe;
	}
	
	
	@Override
	public Informes buscarPorId(long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	
	@Override
	public List<InformeDTO> buscarTodos() {
		return toDto.listInformesToDto(repositorio.findAll());
	}
	
}
