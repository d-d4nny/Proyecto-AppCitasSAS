package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Citas;
import appCitas.AppCitasSASv2.dto.CitasDTO;

public interface IntfCitasServicio {

    /**
     * Registra una nueva cita.
     *
     * @param citasDTO Objeto CitasDTO que representa la información de la cita a registrar.
     * @return Objeto CitasDTO de la cita registrada.
     */
    CitasDTO registrar(CitasDTO citasDTO);

    /**
     * Busca una cita por su identificador.
     *
     * @param id Identificador de la cita.
     * @return Objeto Citas correspondiente al identificador proporcionado.
     */
    Citas buscarPorId(long id);

    /**
     * Obtiene una lista de todas las citas.
     *
     * @return Lista de objetos CitasDTO que representan todas las citas registradas.
     */
    List<CitasDTO> buscarTodos();

    /**
     * Elimina una cita por su identificador.
     *
     * @param id Identificador de la cita a eliminar.
     * @return Objeto CitasDTO eliminado.
     */
    Citas eliminar(long id);

    /**
     * Cancela una cita por su identificador.
     *
     * @param idCita Identificador de la cita a cancelar.
     */
    void cancelarCita(Long idCita);

    /**
     * Marca una cita como completada.
     *
     * @param idCita Identificador de la cita a marcar como completada.
     */
    void completarCita(Long idCita);
}
