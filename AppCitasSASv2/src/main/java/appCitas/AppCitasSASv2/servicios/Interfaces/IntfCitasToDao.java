package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Citas;
import appCitas.AppCitasSASv2.dto.CitasDTO;

public interface IntfCitasToDao {

    /**
     * Convierte un objeto CitasDTO a una entidad Citas.
     *
     * @param citasDTO Objeto CitasDTO a convertir.
     * @return Objeto Citas convertido.
     */
    Citas citasToDao(CitasDTO citasDTO);

    /**
     * Convierte una lista de objetos CitasDTO a una lista de entidades Citas.
     *
     * @param listaCitasDTO Lista de CitasDTO a convertir.
     * @return Lista de Citas convertida.
     */
    List<Citas> listCitasToDao(List<CitasDTO> listaCitasDTO);
}
