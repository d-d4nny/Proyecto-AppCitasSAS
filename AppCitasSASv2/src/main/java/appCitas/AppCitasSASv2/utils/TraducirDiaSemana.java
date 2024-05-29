package appCitas.AppCitasSASv2.utils;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

public class TraducirDiaSemana {

	public static String getDiaSemanaEnEspanol(DayOfWeek dayOfWeek) {
        return dayOfWeek.getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase();
    }
}
