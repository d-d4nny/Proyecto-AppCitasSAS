package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dto.ConsultaTurnoDTO;
import appCitas.AppCitasSASv2.repositorios.ConsultaTurnoRepositorio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfConsultaTurnoServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfConsultaTurnoToDao;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfConsultaTurnoToDto;
import jakarta.persistence.PersistenceException;

@Service
public class ImplConsultaTurnoServicio implements IntfConsultaTurnoServicio {
	
	@Autowired
	private IntfConsultaTurnoToDao toDao;
	
	@Autowired
	private IntfConsultaTurnoToDto toDto;
	
	@Autowired
	private ConsultaTurnoRepositorio repositorio;
	
	@Autowired
	private ImplDoctorServicio doctoresServicio;
	
	
	@Override
	public ConsultaTurnoDTO registrar(ConsultaTurnoDTO consultaTurnoDto) {
		
		try {
			repositorio.save(toDao.consultaTurnoToDao(consultaTurnoDto));
			return consultaTurnoDto;
		}catch (Exception e) {
			System.out.println("\n[ERROR ImplConsultaTurnoServicio - registrar()] - Al registrar consultaTurno (return null): " + e);
			return null;
		}
	}
	
	
	@Override
	public void actualizarConsultaTurno(ConsultaTurnoDTO consultaTurnoModificado) {

		try {
			ConsultaTurno consultaTurnoActual = repositorio.findById(consultaTurnoModificado.getIdConsultaTurno()).orElse(null);

			consultaTurnoActual.setNumConsulta(consultaTurnoModificado.getNumConsulta());
			consultaTurnoActual.setTramoHoraTurnoInicio(consultaTurnoModificado.getTramoHoraTurnoInicio());
			consultaTurnoActual.setTramoHoraTurnoFin(consultaTurnoModificado.getTramoHoraTurnoFin());
			
			repositorio.save(consultaTurnoActual);
		} catch (PersistenceException pe) {
			System.out.println(
					"[Error ConsultaTurnoServicioImpl - actualizarConsultaTurno()] Al modificar la consultaTurno " + pe.getMessage());

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
