package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Citas;
import appCitas.AppCitasSASv2.dto.CitasDTO;

public interface IntfCitasToDto {

    /**
     * Convierte un objeto Citas a un DTO CitasDTO.
     *
     * @param citas Objeto Citas a convertir.
     * @return Objeto CitasDTO convertido.
     */
    CitasDTO citasToDto(Citas citas);

    /**
     * Convierte una lista de objetos Citas a una lista de DTO CitasDTO.
     *
     * @param listaCitas Lista de Citas a convertir.
     * @return Lista de CitasDTO convertida.
     */
    List<CitasDTO> listCitasToDto(List<Citas> listaCitas);
}
