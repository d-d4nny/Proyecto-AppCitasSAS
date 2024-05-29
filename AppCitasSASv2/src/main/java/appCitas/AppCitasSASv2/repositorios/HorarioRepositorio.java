package appCitas.AppCitasSASv2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import appCitas.AppCitasSASv2.dao.Horarios;

public interface HorarioRepositorio extends JpaRepository<Horarios, Long> {

	public Horarios findFirstByIdHorario (long idHorario);

    public boolean existsByIdHorario(long idHorario);
}
