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

    /**
     * Registra una nueva ConsultaTurno.
     * 
     * @param consultaTurnoDto Datos de la consultaTurno a registrar.
     * @return ConsultaTurnoDTO objeto con los datos de la consultaTurno registrada.
     */
    @Override
    public ConsultaTurnoDTO registrar(ConsultaTurnoDTO consultaTurnoDto) {

        try {
            repositorio.save(toDao.consultaTurnoToDao(consultaTurnoDto));
            return consultaTurnoDto;
        } catch (Exception e) {
            System.out.println("\n[ERROR ImplConsultaTurnoServicio - registrar()] - Al registrar consultaTurno (return null): " + e);
            return null;
        }
    }

    /**
     * Actualiza los datos de una ConsultaTurno.
     * 
     * @param consultaTurnoModificado Datos actualizados de la consultaTurno.
     */
    @Override
    public void actualizarConsultaTurno(ConsultaTurnoDTO consultaTurnoModificado) {

        try {
            ConsultaTurno consultaTurnoActual = repositorio.findById(consultaTurnoModificado.getIdConsultaTurno()).orElse(null);

            consultaTurnoActual.setNumConsulta(consultaTurnoModificado.getNumConsulta());
            consultaTurnoActual.setNombreConsulta(consultaTurnoModificado.getNombreConsulta());

            repositorio.save(consultaTurnoActual);
        } catch (PersistenceException pe) {
            System.out.println("[Error ConsultaTurnoServicioImpl - actualizarConsultaTurno()] Al modificar la consultaTurno " + pe.getMessage());
        }
    }

    /**
     * Elimina una ConsultaTurno por su ID.
     * 
     * @param id ID de la consultaTurno a eliminar.
     * @return ConsultaTurno objeto eliminado o null si no se encontró.
     */
    @Override
    public ConsultaTurno eliminar(long id) {
        ConsultaTurno consultaTurno = repositorio.findById(id).orElse(null);
        if (consultaTurno != null) {
            repositorio.delete(consultaTurno);
        }
        return consultaTurno;
    }

    /**
     * Busca una ConsultaTurno por su ID.
     * 
     * @param id ID de la consultaTurno a buscar.
     * @return ConsultaTurno objeto encontrado o null si no se encontró.
     */
    @Override
    public ConsultaTurno buscarPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    /**
     * Obtiene la lista de todas las ConsultaTurno.
     * 
     * @return Lista de ConsultaTurnoDTO que representan todas las consultasTurno.
     */
    @Override
    public List<ConsultaTurnoDTO> buscarTodos() {
        return toDto.listConsultaTurnoToDto(repositorio.findAll());
    }
}
