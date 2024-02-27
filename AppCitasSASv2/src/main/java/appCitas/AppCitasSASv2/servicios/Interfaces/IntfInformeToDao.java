package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Informes;
import appCitas.AppCitasSASv2.dto.InformeDTO;

public interface IntfInformeToDao {

    /**
     * Convierte un objeto InformeDTO a un objeto Informes.
     *
     * @param informeDTO Objeto InformeDTO a convertir.
     * @return Objeto Informes resultante de la conversión.
     */
    Informes informeToDao(InformeDTO informeDTO);

    /**
     * Convierte una lista de InformeDTO a una lista de Informes.
     *
     * @param listaInformesDTO Lista de InformeDTO a convertir.
     * @return Lista de Informes resultante de la conversión.
     */
    List<Informes> listInformesToDao(List<InformeDTO> listaInformesDTO);
}
