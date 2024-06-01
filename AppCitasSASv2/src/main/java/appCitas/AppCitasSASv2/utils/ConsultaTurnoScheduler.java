package appCitas.AppCitasSASv2.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import appCitas.AppCitasSASv2.servicios.Interfaces.IntfConsultaTurnoServicio;

@Component 
public class ConsultaTurnoScheduler {
	
	@Autowired
    private IntfConsultaTurnoServicio consultaTurnoService;

    // Esta tarea se ejecutará todos los domingos a las 12:00 AM
	@Scheduled(cron = "0 0 23 ? * SUN")
    public void resetearDoctor() {
        // Obtiene la fecha actual
        LocalDate today = LocalDate.now();

        // Si hoy es domingo, resetea el doctor en todas las instancias de ConsultaTurno
        if (today.getDayOfWeek() == DayOfWeek.SUNDAY) {
            consultaTurnoService.resetearDoctor();
        }
    }
}
