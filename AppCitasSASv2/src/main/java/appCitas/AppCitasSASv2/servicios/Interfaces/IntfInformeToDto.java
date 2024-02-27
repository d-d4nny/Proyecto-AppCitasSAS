package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Informes;
import appCitas.AppCitasSASv2.dto.InformeDTO;

public interface IntfInformeToDto {

    /**
     * Convierte un objeto Informes a un objeto InformeDTO.
     *
     * @param u Objeto Informes a convertir.
     * @return Objeto InformeDTO resultante de la conversión.
     */
    InformeDTO informeToDto(Informes u);

    /**
     * Convierte una lista de Informes a una lista de InformeDTO.
     *
     * @param listaInformes Lista de Informes a convertir.
     * @return Lista de InformeDTO resultante de la conversión.
     */
    List<InformeDTO> listInformesToDto(List<Informes> listaInformes);
}
