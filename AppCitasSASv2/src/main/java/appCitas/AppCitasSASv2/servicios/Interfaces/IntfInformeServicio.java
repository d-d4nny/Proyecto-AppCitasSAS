package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Informes;
import appCitas.AppCitasSASv2.dto.InformeDTO;

public interface IntfInformeServicio {

    /**
     * Crea un nuevo informe.
     *
     * @param informeDTO Datos del informe a crear.
     * @return true si la operación fue exitosa, false si ocurrió un error.
     */
    Boolean crearInforme(InformeDTO informeDTO);

    /**
     * Busca un informe por su ID.
     *
     * @param id ID del informe a buscar.
     * @return El informe encontrado o null si no existe.
     */
    Informes buscarPorId(long id);

    /**
     * Busca todos los informes disponibles.
     *
     * @return Lista de informes DTO.
     */
    List<InformeDTO> buscarTodos();

    /**
     * Elimina un informe por su ID.
     *
     * @param id ID del informe a eliminar.
     * @return El informe eliminado o null si no existe.
     */
    Informes eliminar(long id);
}
