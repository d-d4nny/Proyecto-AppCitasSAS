package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;

public interface IntfDoctorToDto {

    /**
     * Convierte un objeto Doctores a un objeto DoctoresDTO.
     *
     * @param doctores Objeto Doctores que se va a convertir.
     * @return Objeto DoctoresDTO correspondiente al Doctores proporcionado.
     */
    DoctoresDTO doctoresToDto(Doctores doctores);

    /**
     * Convierte una lista de objetos Doctores a una lista de objetos DoctoresDTO.
     *
     * @param listaDoctores Lista de objetos Doctores que se va a convertir.
     * @return Lista de objetos DoctoresDTO correspondiente a la lista de Doctores proporcionada.
     */
    List<DoctoresDTO> listDoctoresToDto(List<Doctores> listaDoctores);
}
