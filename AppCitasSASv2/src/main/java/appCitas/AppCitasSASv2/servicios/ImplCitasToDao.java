package appCitas.AppCitasSASv2.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Citas;
import appCitas.AppCitasSASv2.dto.CitasDTO;

@Service
public class ImplCitasToDao implements IntfCitasToDao {

	@Override
	public Citas citasToDao(CitasDTO citasDTO) {

		Citas citasDao = new Citas();
		
		try {
			citasDao.setIdCita(citasDTO.getIdCita());
			citasDao.setFechaCita(citasDTO.getFechaCita());
			citasDao.setHoraCita(citasDTO.getHoraCita());
			citasDao.setMotivoCita(citasDTO.getMotivoCita());
			citasDao.setEstadoCita(citasDTO.getEstadoCita());
			citasDao.setPaciente(citasDTO.getPaciente());
			citasDao.setEmpleado(citasDTO.getDoctor());
			
			return citasDao;
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR CitasToDaoImpl - citasToDao()] - Error al convertir citas DTO a citas DAO (return null): "
							+ e);
			return null;
		}
	}
	
	@Override
	public List<Citas> listCitasToDao(List<CitasDTO> listaCitasDTO){
		
		List<Citas> listaCitasDao = new ArrayList<>();
		
		try {
			for (CitasDTO citasDTO : listaCitasDTO) {
				listaCitasDao.add(citasToDao(citasDTO));
			}
			return listaCitasDao;
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR CitasToDaoImpl - listCitasToDao()] - Error al convertir lista de citas DTO a lista de citas DAO (return null): "
							+ e);
		}
		return null;
	}
}
