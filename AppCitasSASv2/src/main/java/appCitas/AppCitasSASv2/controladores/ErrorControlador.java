package appCitas.AppCitasSASv2.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorControlador {
	
	@GetMapping("/error")
    public String handleError() {
		return "error";
    }
}
