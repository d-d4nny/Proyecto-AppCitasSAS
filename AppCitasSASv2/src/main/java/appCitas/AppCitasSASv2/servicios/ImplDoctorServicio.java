package appCitas.AppCitasSASv2.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;
import appCitas.AppCitasSASv2.repositorios.DoctorRepositorio;

@Service
public class ImplDoctorServicio implements IntfDoctorServicio {

	
	@Autowired
	private IntfDoctorToDao toDao;
	
	@Autowired
	private IntfDoctorToDto toDto;
	
	@Autowired
	private DoctorRepositorio repositorio;
	
	
	@Override
	public DoctoresDTO registrar(DoctoresDTO doctoresDto, ConsultaTurno consultaTurno) {
		try {
			Doctores doctorDaoByNombreCompleto = repositorio.findFirstByNombreCompletoDoctor(doctoresDto.getNombreCompletoDoctor());
			
			if (doctorDaoByNombreCompleto != null) {
				return null; // Si no es null es que ya esta registrado
			}
			
			boolean yaExisteElId = repositorio.existsById(doctoresDto.getIdDoctor());
			
			if(yaExisteElId) {
				return doctoresDto;
			}
			
			Doctores doctorDao = toDao.doctoresToDao(doctoresDto, consultaTurno);
			repositorio.save(doctorDao);
			
			return doctoresDto;
			
		}catch (IllegalArgumentException iae) {
			System.out.println("[Error ImplDoctorServicio - registrar() ]" + iae.getMessage());
		} catch (Exception e) {
			System.out.println("[Error ImplDoctorServicio - registrar() ]" + e.getMessage());
		}
		return null;
	}
	
	@Override
	public Doctores eliminar(long id) {
		Doctores doctor = repositorio.findById(id).orElse(null);
		if (doctor != null) {
			repositorio.delete(doctor);
		}
		return doctor;
	}
	
	@Override
	public Doctores buscarPorId(long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	@Override
	public List<DoctoresDTO> buscarTodos() {
		return toDto.listDoctoresToDto(repositorio.findAll());
	}
}
