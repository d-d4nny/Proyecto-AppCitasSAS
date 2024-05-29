package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Horarios;
import appCitas.AppCitasSASv2.dto.HorariosDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfHorarioToDto;

@Service
public class ImplHorarioToDto implements IntfHorarioToDto {
	
    @Override
    public HorariosDTO horariosToDto(Horarios u) {

        try {
        	HorariosDTO dto = new HorariosDTO();

            dto.setIdHorario(u.getIdHorario());
            dto.setDiaSemana(u.getDiaSemana());
            dto.setTramoHorarioInicio(u.getTramoHorarioInicio());
            dto.setTramoHorarioFin(u.getTramoHorarioFin());

            return dto;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR horarioToDaoImpl - horarioToDto()] - Error al convertir horario DAO a horario DTO (return null): "
                            + e);
            return null;
        }
    }

    @Override
    public List<HorariosDTO> listHorariosToDto(List<Horarios> listaHorarios) {
        try {

            List<HorariosDTO> listaDto = new ArrayList<>();
            for (Horarios u : listaHorarios) {
                listaDto.add(this.horariosToDto(u));
            }
            return listaDto;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR horarioToDaoImpl - listHorariosToDto()] - Error al convertir lista de horario DAO a lista de horario DTO (return null): "
                            + e);
        }
        return null;
    }
}
