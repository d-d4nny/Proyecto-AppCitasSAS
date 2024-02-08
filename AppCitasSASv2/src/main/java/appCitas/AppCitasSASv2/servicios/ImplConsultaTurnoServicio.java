package appCitas.AppCitasSASv2.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dto.ConsultaTurnoDTO;
import appCitas.AppCitasSASv2.repositorios.ConsultaTurnoRepositorio;

@Service
public class ImplConsultaTurnoServicio implements IntfConsultaTurnoServicio {
	
	@Autowired
	private IntfConsultaTurnoToDao toDao;
	
	@Autowired
	private IntfConsultaTurnoToDto toDto;
	
	@Autowired
	private ConsultaTurnoRepositorio repositorio;
	
	
	@Override
	public Boolean crearConsultaTurno(ConsultaTurnoDTO consultaTurnoDto) {
		try {
			repositorio.save(toDao.consultaTurnoToDao(consultaTurnoDto));
			return true;
		}catch (Exception e) {
			System.out.println("\n[ERROR ImplConsultaTurnoServicio - crearConsultaTurno()] - Al crear consultaTurno (return false): " + e);
			return false;
		}
	}
	
	
	@Override
	public ConsultaTurno eliminar(long id) {
		ConsultaTurno consultaTurno = repositorio.findById(id).orElse(null);
		if (consultaTurno != null) {
			repositorio.delete(consultaTurno);
		}
		return consultaTurno;
	}
	
	
	@Override
	public ConsultaTurno buscarPorId(long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	
	@Override
	public List<ConsultaTurnoDTO> buscarTodos() {
		return toDto.listConsultaTurnoToDto(repositorio.findAll());
	}
}
