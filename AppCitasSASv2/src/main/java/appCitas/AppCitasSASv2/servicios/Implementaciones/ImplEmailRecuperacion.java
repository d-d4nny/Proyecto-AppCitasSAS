package appCitas.AppCitasSASv2.servicios.Implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.servicios.Interfaces.IntfEmailRecuperacion;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class ImplEmailRecuperacion implements IntfEmailRecuperacion {
    
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Envía un correo electrónico para la recuperación de contraseña.
     * 
     * @param emailDestino Correo electrónico del destinatario.
     * @param nombrePaciente Nombre del paciente.
     * @param token Token para la recuperación de contraseña.
     */
    @Override
    public void enviarEmailRecuperacion(String emailDestino, String nombrePaciente, String token) {
        try {
            MimeMessage mensaje = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

            // Configuración del correo electrónico
            helper.setFrom("digitalhealthresponse@gmail.com"); // AQUÍ VA EL EMAIL DEL .PROPERTIES
            helper.setTo(emailDestino);
            helper.setSubject("RESTABLECER CONTRASEÑA");

            // Construcción del cuerpo del mensaje
            String urlDominio = "https://appcitassas.glezmanz.eu";
            String urlDeRecuperacion = String.format("%s/auth/recuperar?token=%s", urlDominio, token);
            String cuerpoMensaje = String.format(
    				"﻿<!DOCTYPE html> <html lang='es'> <body> <div style='width: 600px; padding: 20px; border: 2px solid #ff9900; border-radius: 12px;"
    				+ " font-family: Sans-serif'> <h1 style='color:#192255'>Restablecer contraseña<b style='color:#ff9900'> App Citas SAS</b></h1>"
    				+ " <p style='margin-bottom:25px'>Estimado/a&nbsp;<b>%s</b>:</p> <p style='margin-bottom:25px'>"
    				+ "Recibiste este correo porque se solicitó un restablecimiento de contraseña para tu cuenta. Haz clic en el botón que aparece a continuación para cambiar tu contraseña.</p>"
    				+ " <a style='padding: 10px 15px; border-radius: 20px; background-color: #285845; color: white; text-decoration: none' href='%s' target='_blank'>Cambiar contraseña</a>"
    				+ " <p style='margin-top:25px'>Si no solicitaste este restablecimiento de contraseña, puedes ignorar este correo de forma segura.</p>"
    				+ " <p>Gracias por utilizar nuestros servicios.</p> </div> </body> </html>",
    				nombrePaciente, urlDeRecuperacion);

            helper.setText(cuerpoMensaje, true);

            // Envío del correo electrónico
            javaMailSender.send(mensaje);

        } catch (MailException me) {
            System.out.println("[Error EmailServicioImpl - enviarEmailRecuperacion()] Ha ocurrido un error al enviar el email! " + me.getMessage());
        } catch (MessagingException e) {
            System.out.println("[Error EmailServicioImpl - enviarEmailRecuperacion()] Ha ocurrido un error al enviar el email! " + e.getMessage());
        }			
    }

    /**
     * Envía un correo electrónico de confirmación de cuenta.
     * 
     * @param emailDestino Correo electrónico del destinatario.
     * @param nombrePaciente Nombre del paciente.
     * @param token Token para la confirmación de cuenta.
     */
    @Override
    public void enviarEmailConfirmacion(String emailDestino, String nombrePaciente, String token) {
        try {
            MimeMessage mensaje = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

            // Configuración del correo electrónico
            helper.setFrom("digitalhealthresponse@gmail.com");
            helper.setTo(emailDestino);
            helper.setSubject("Confirmación de cuenta AppCitasSAS");

            // Construcción del cuerpo del mensaje
            String urlDominio = "https://appcitassas.glezmanz.eu";
            String urlDeConfirmacion = String.format("%s/auth/confirmar-cuenta?token=%s", urlDominio, token);
            String cuerpoMensaje = String.format(
                    "﻿<!DOCTYPE html> <html lang='es'> <body> <div style='width: 600px; padding: 20px; border: 2px solid black; border-radius: 13px; background-color: #DEDEDE; font-family: Sans-serif'> <h1 style='color:#1f3c85'>Confirmar cuenta<b style='color:#5993d3'> App Citas SAS</b></h1>"
                    + " <p style='margin-bottom:25px'>Estimado/a&nbsp;<b>%s</b>:</p> <p style='margin-bottom:25px'>"
                    + "Bienvenido/a a App Citas SAS. Para confirmar tu cuenta, haz clic en el botón que aparece a continuación:</p>"
                    + " <a style='padding: 10px 15px; border-radius: 10px; background-color: #5993d3; color: white; text-decoration: none' href='%s' target='_blank'>Confirmar cuenta</a>"
                    + " <p style='margin-top:25px'>Gracias por unirte a App Citas SAS.</p> </div> </body> </html>",
                    nombrePaciente, urlDeConfirmacion);

            helper.setText(cuerpoMensaje, true);

            // Envío del correo electrónico
            javaMailSender.send(mensaje);

        } catch (MailException me) {
            System.out.println("[Error EmailServicioImpl - enviarEmailConfirmacion()] Ha ocurrido un error al enviar el email! " + me.getMessage());
        } catch (MessagingException e) {
            System.out.println("[Error EmailServicioImpl - enviarEmailConfirmacion()] Ha ocurrido un error al enviar el email! " + e.getMessage());
        }
    }
}
