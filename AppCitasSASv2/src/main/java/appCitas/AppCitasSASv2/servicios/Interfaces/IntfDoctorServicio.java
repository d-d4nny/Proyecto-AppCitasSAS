package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;

public interface IntfDoctorServicio {

    /**
     * Registra un nuevo doctor.
     *
     * @param doctoresDTO Objeto DoctoresDTO que contiene la información del doctor a registrar.
     * @return Objeto DoctoresDTO del doctor registrado.
     */
    DoctoresDTO registrar(DoctoresDTO doctoresDTO);

    /**
     * Actualiza la información de un doctor.
     *
     * @param doctoresDTO Objeto DoctoresDTO que contiene la información actualizada del doctor.
     */
    void actualizarDoctor(DoctoresDTO doctoresDTO);

    /**
     * Busca un doctor por su ID.
     *
     * @param id ID del doctor a buscar.
     * @return Objeto Doctores correspondiente al ID proporcionado.
     */
    Doctores buscarPorId(long id);

    /**
     * Obtiene una lista de todos los doctores registrados.
     *
     * @return Lista de objetos DoctoresDTO que representan a todos los doctores.
     */
    List<DoctoresDTO> buscarTodos();

    /**
     * Elimina un doctor por su ID.
     *
     * @param id ID del doctor a eliminar.
     * @return Objeto Doctores eliminado.
     */
    Doctores eliminar(long id);
}
