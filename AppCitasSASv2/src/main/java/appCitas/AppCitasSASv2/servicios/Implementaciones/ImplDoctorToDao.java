package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dao.Informes;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;
import appCitas.AppCitasSASv2.dto.InformeDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfDoctorToDao;

@Service
public class ImplDoctorToDao implements IntfDoctorToDao {

    /**
     * Convierte un objeto DoctoresDTO a un objeto Doctores.
     * 
     * @param doctoresDTO Objeto DoctoresDTO a convertir.
     * @return Objeto Doctores convertido o null si hay un error.
     */
    @Override
    public Doctores doctoresToDao(DoctoresDTO doctoresDTO) {

        Doctores doctoresDao = new Doctores();

        try {
            doctoresDao.setIdDoctor(doctoresDTO.getIdDoctor());
            doctoresDao.setNombreCompletoDoctor(doctoresDTO.getNombreCompletoDoctor());
            doctoresDao.setEspecialidadDoctor(doctoresDTO.getEspecialidadDoctor());

            return doctoresDao;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR doctorToDaoImpl - doctorToDao()] - Error al convertir doctor DTO a doctor DAO (return null): "
                            + e);
            return null;
        }
    }

    /**
     * Convierte una lista de objetos DoctoresDTO a una lista de objetos Doctores.
     * 
     * @param listaDoctoresDTO Lista de DoctoresDTO a convertir.
     * @return Lista de Doctores convertida o null si hay un error.
     */
    @Override
    public List<Doctores> listDoctoresToDao(List<DoctoresDTO> listaDoctoresDTO) {
    	List<Doctores> listaDoctoresDao = new ArrayList<>();

        try {
            for (DoctoresDTO doctoresDTO : listaDoctoresDTO) {
            	listaDoctoresDao.add(doctoresToDao(doctoresDTO));
            }
            return listaDoctoresDao;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR doctorToDaoImpl - listDoctorToDao()] - Error al convertir lista de doctores DTO a lista de doctores DAO (return null): "
                            + e);
        }
        return null;
    }
}
