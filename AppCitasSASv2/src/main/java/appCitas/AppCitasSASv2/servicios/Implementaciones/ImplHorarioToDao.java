package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Horarios;
import appCitas.AppCitasSASv2.dto.HorariosDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfHorarioToDao;

@Service
public class ImplHorarioToDao implements IntfHorarioToDao{
	
    @Override
    public Horarios horariosToDao(HorariosDTO horariosDTO) {

    	Horarios horariosDao = new Horarios();

        try {
        	horariosDao.setIdHorario(horariosDTO.getIdHorario());
        	horariosDao.setDiaSemana(horariosDTO.getDiaSemana());
			horariosDao.setTramoHorarioInicio(horariosDTO.getTramoHorarioInicio());
			horariosDao.setTramoHorarioFin(horariosDTO.getTramoHorarioFin());

            return horariosDao;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR horarioToDaoImpl - horarioToDao()] - Error al convertir horario DTO a horario DAO (return null): "
                            + e);
            return null;
        }
    }

    @Override
    public List<Horarios> listHorariosToDao(List<HorariosDTO> listaHorariosDTO) {
    	List<Horarios> listaHorariosDao = new ArrayList<>();

        try {
            for (HorariosDTO horariosDTO : listaHorariosDTO) {
            	listaHorariosDao.add(horariosToDao(horariosDTO));
            }
            return listaHorariosDao;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR horarioToDaoImpl - listHorarioToDao()] - Error al convertir lista de horarios DTO a lista de horarios DAO (return null): "
                            + e);
        }
        return null;
    }
}
