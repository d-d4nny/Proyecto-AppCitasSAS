package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Citas;
import appCitas.AppCitasSASv2.dto.CitasDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfCitasToDto;

@Service
public class ImplCitasToDto implements IntfCitasToDto {

    /**
     * Convierte un objeto Citas a un objeto CitasDTO.
     * 
     * @param u Objeto Citas a convertir.
     * @return Objeto CitasDTO convertido o null si hay un error.
     */
    @Override
    public CitasDTO citasToDto(Citas u) {

        try {
            CitasDTO dto = new CitasDTO();

            dto.setIdCita(u.getIdCita());
            dto.setFechaCita(u.getFechaCita());
            dto.setHoraCita(u.getHoraCita());
            dto.setMotivoCita(u.getMotivoCita());
            dto.setEstadoCita(u.getEstadoCita());
            dto.setPaciente(u.getPaciente());
            dto.setDoctor(u.getEmpleado());

            return dto;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR CitasToDtoImpl - citasToDto()] - Error al convertir citas DAO a citasToDto (return null): "
                            + e);
            return null;
        }
    }

    /**
     * Convierte una lista de objetos Citas a una lista de objetos CitasDTO.
     * 
     * @param listaCitas Lista de Citas a convertir.
     * @return Lista de CitasDTO convertida o null si hay un error.
     */
    @Override
    public List<CitasDTO> listCitasToDto(List<Citas> listaCitas) {
        try {

            List<CitasDTO> listaDto = new ArrayList<>();
            for (Citas u : listaCitas) {
                listaDto.add(this.citasToDto(u));
            }
            return listaDto;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR CitasToDtoImpl - listaCitasToDto()] - Error al convertir lista de citas DAO a lista de citasDTO (return null): "
                            + e);
        }
        return null;
    }
}
