package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import appCitas.AppCitasSASv2.dao.Horarios;
import appCitas.AppCitasSASv2.dto.HorariosDTO;
import appCitas.AppCitasSASv2.repositorios.HorarioRepositorio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfHorarioServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfHorarioToDao;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfHorarioToDto;
import jakarta.persistence.PersistenceException;

public class ImplHorarioServicio implements IntfHorarioServicio {
	
	@Autowired
    private IntfHorarioToDao toDao;

    @Autowired
    private IntfHorarioToDto toDto;

    @Autowired
    private HorarioRepositorio repositorio;

    @Override
    public HorariosDTO registrar(HorariosDTO horiariosDto) {
        try {
        	Horarios horarioDaoById = repositorio.findFirstByIdHorario(horiariosDto.getIdHorario());

            if (horarioDaoById != null) {
                return null; // Si no es null es que ya está registrado
            }

            boolean yaExisteElId = repositorio.existsByIdHorario(horiariosDto.getIdHorario());

            if (yaExisteElId) {
                return horiariosDto;
            }

            Horarios horarioDao = toDao.horariosToDao(horiariosDto);
            repositorio.save(horarioDao);

            return horiariosDto;

        } catch (IllegalArgumentException iae) {
            System.out.println("[Error ImplHorarioServicio - registrar() ]" + iae.getMessage());
        } catch (Exception e) {
            System.out.println("[Error ImplHorarioServicio - registrar() ]" + e.getMessage());
        }
        return null;
    }

    @Override
    public Horarios eliminar(long id) {
    	Horarios horario = repositorio.findById(id).orElse(null);
        if (horario != null) {
            repositorio.delete(horario);
        }
        return horario;
    }

    @Override
    public Horarios buscarPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public List<HorariosDTO> buscarTodos() {
        return toDto.listHorariosToDto(repositorio.findAll());
    }
}
