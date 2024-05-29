package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Citas;
import appCitas.AppCitasSASv2.dto.CitasDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfCitasToDao;

@Service
public class ImplCitasToDao implements IntfCitasToDao {

    /**
     * Convierte un objeto CitasDTO a un objeto Citas.
     * 
     * @param citasDTO Objeto CitasDTO a convertir.
     * @return Objeto Citas convertido o null si hay un error.
     */
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
            citasDao.setConsultaTurno(citasDTO.getConsultaTurno());

            return citasDao;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR CitasToDaoImpl - citasToDao()] - Error al convertir citas DTO a citas DAO (return null): "
                            + e);
            return null;
        }
    }

    /**
     * Convierte una lista de objetos CitasDTO a una lista de objetos Citas.
     * 
     * @param listaCitasDTO Lista de CitasDTO a convertir.
     * @return Lista de Citas convertida o null si hay un error.
     */
    @Override
    public List<Citas> listCitasToDao(List<CitasDTO> listaCitasDTO) {

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
