package appCitas.AppCitasSASv2.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;

@Service
public class ImplDoctorToDao implements IntfDoctorToDao {

	@Override
	public Doctores doctoresToDao(DoctoresDTO doctoresDTO, ConsultaTurno consultaTurno) {

		Doctores doctoresDao = new Doctores();

		try {
			doctoresDao.setIdDoctor(doctoresDTO.getIdDoctor());
			doctoresDao.setNombreCompletoDoctor(doctoresDTO.getNombreCompletoDoctor());
			doctoresDao.setEspecialidadDoctor(doctoresDTO.getEspecialidadDoctor());
			doctoresDao.setConsultaTurno(consultaTurno);
			
			return doctoresDao;
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR doctorToDaoImpl - doctorToDao()] - Error al convertir doctor DTO a doctor DAO (return null): "
							+ e);
			return null;
		}
	}
	
	@Override
	public List<Doctores> listDoctoresToDao(List<DoctoresDTO> listaDoctoresDTO){
		// TODO Auto-generated method stub
		return null;
	}
}
