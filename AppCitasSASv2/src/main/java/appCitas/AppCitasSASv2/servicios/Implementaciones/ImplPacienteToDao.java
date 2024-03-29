package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.PacienteDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfPacienteToDao;

@Service
public class ImplPacienteToDao implements IntfPacienteToDao {

    /**
     * Convierte un objeto PacienteDTO a un objeto Paciente.
     *
     * @param pacienteDTO Objeto PacienteDTO a convertir.
     * @return Objeto Paciente convertido.
     */
    @Override
    public Paciente pacienteToDao(PacienteDTO pacienteDTO) {
        try {
            ImplPacienteServicio iPac = new ImplPacienteServicio();
            Paciente pacienteDao = new Paciente();

            pacienteDao.setNombreCompletoPaciente(pacienteDTO.getNombreCompletoPaciente());
            pacienteDao.setDniPaciente(pacienteDTO.getDniPaciente());
            pacienteDao.setTlfPaciente(pacienteDTO.getTlfPaciente());
            pacienteDao.setEmailPaciente(pacienteDTO.getEmailPaciente());
            pacienteDao.setContrasenaPaciente(pacienteDTO.getContrasenaPaciente());
            pacienteDao.setGeneroPaciente(pacienteDTO.getGeneroPaciente());
            pacienteDao.setDireccionPaciente(pacienteDTO.getDireccionPaciente());
            pacienteDao.setRolPaciente(pacienteDTO.getRolPaciente());
            pacienteDao.setProfilePicture(iPac.convertToByteArray(pacienteDTO.getProfilePicture()));
            pacienteDao.setCuentaConfirmada(pacienteDTO.isCuentaConfirmada());
            pacienteDao.setExpiracionToken(pacienteDTO.getExpiracionToken());

            return pacienteDao;

        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR ImplPacienteToDao - pacienteToDao()] - Al convertir pacienteDTO a pacienteDAO (return null): "
                            + e);
            return null;
        }
    }

    /**
     * Convierte una lista de objetos PacienteDTO a una lista de objetos Paciente.
     *
     * @param listaPacienteDTO Lista de objetos PacienteDTO a convertir.
     * @return Lista de objetos Paciente convertidos.
     */
    @Override
    public List<Paciente> listPacienteToDao(List<PacienteDTO> listaPacienteDTO) {
        List<Paciente> listaPacienteDao = new ArrayList<>();

        try {
            for (PacienteDTO pacienteDTO : listaPacienteDTO) {
                listaPacienteDao.add(pacienteToDao(pacienteDTO));
            }

            return listaPacienteDao;

        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR ImplPacienteToDao - listPacienteToDao()] - Al convertir lista de pacienteDTO a lista de pacienteDAO (return null): "
                            + e);
        }
        return null;
    }
}
