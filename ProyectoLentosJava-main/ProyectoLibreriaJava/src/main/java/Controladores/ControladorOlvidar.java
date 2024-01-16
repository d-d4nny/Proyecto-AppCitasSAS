package Controladores;

import Servicios.ImplentacionIntereaccionUsuario;
import Utilidades.Encriptado;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Aqui va el controlador es para el formulario para restablecer enviar el correo
public class ControladorOlvidar extends HttpServlet{


    private static final long serialVersionUID = 1L;
	

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
		 try
		 {
			 response.getWriter().append("Served at: ").append(request.getContextPath());
		 }catch(Exception e) {

				System.out.println("[ERROR-ControladorOlvidar-doGet] Se produjo un error en el metodo get");
			}
		
		}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
     
        
        String correo=request.getParameter("emailUsuario");
        ImplentacionIntereaccionUsuario cosa = new ImplentacionIntereaccionUsuario();
        
        if(correo!=null)
        	cosa.OlvidarClaveUsuario(correo);
        	
        
       
        

           
        
    }
}
