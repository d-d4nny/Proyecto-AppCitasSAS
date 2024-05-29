package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Citas;
import appCitas.AppCitasSASv2.dto.CitasDTO;
import appCitas.AppCitasSASv2.repositorios.CitasRepositorio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfCitasServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfCitasToDao;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfCitasToDto;

@Service
public class ImplCitasServicio implements IntfCitasServicio {

    @Autowired
    private IntfCitasToDao toDao;

    @Autowired
    private IntfCitasToDto toDto;

    @Autowired
    private CitasRepositorio repositorio;

    /**
     * Registra una nueva cita.
     * 
     * @param citasDto Datos de la cita a registrar.
     * @return CitasDTO objeto con los datos de la cita registrada.
     */
    @Override
    public CitasDTO registrar(CitasDTO citasDto) {
        try {
            Citas citasDaoById = repositorio.findFirstByIdCita(citasDto.getIdCita());

            if (citasDaoById != null) {
                return null; // Si no es null es que ya está registrado
            }

            boolean yaExisteElId = repositorio.existsById(citasDto.getIdCita());

            if (yaExisteElId) {
                return citasDto;
            }

            Citas citasDao = toDao.citasToDao(citasDto);
            repositorio.save(citasDao);

            return citasDto;
        } catch (IllegalArgumentException iae) {
            System.out.println("[Error ImplCitasServicio - registrar() ]" + iae.getMessage());
        } catch (Exception e) {
            System.out.println("[Error ImplCitasServicio - registrar() ]" + e.getMessage());
        }
        return null;
    }

    /**
     * Elimina una cita por su ID.
     * 
     * @param id ID de la cita a eliminar.
     * @return Citas objeto eliminado o null si no se encontró.
     */
    @Override
    public Citas eliminar(long id) {
        Citas cita = repositorio.findById(id).orElse(null);
        if (cita != null) {
            repositorio.delete(cita);
        }
        return cita;
    }

    /**
     * Busca una cita por su ID.
     * 
     * @param id ID de la cita a buscar.
     * @return Citas objeto encontrado o null si no se encontró.
     */
    @Override
    public Citas buscarPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    /**
     * Obtiene la lista de todas las citas.
     * 
     * @return Lista de CitasDTO que representan todas las citas.
     */
    @Override
    public List<CitasDTO> buscarTodos() {
        return toDto.listCitasToDto(repositorio.findAll());
    }
    
    
    /**
     * Cancela una cita por su ID.
     * 
     * @param idCita ID de la cita a cancelar.
     */
    @Override
    public void cancelarCita(Long idCita) {
        try {
            Citas cita = repositorio.findById(idCita).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
            cita.setEstadoCita("Cancelada");
            repositorio.save(cita);
        } catch (Exception e) {
            System.out.println("[Error ImplCitasServicio - cancelarCita() ]" + e.getMessage());
        }
    }

    /**
     * Completa una cita por su ID.
     * 
     * @param idCita ID de la cita a completar.
     */
    @Override
    public void completarCita(Long idCita) {
        try {
            Citas cita = repositorio.findById(idCita).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
            cita.setEstadoCita("Completada");
            repositorio.save(cita);
        } catch (Exception e) {
            System.out.println("[Error ImplCitasServicio - completarCita() ]" + e.getMessage());
        }
    }
}
