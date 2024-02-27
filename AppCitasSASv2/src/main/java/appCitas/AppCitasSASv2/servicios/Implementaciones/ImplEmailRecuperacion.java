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
            helper.setFrom("danitbp12@gmail.com"); // AQUÍ VA EL EMAIL DEL .PROPERTIES
            helper.setTo(emailDestino);
            helper.setSubject("RESTABLECER CONTRASEÑA");

            // Construcción del cuerpo del mensaje
            String urlDominio = "http://localhost:8080";
            String urlDeRecuperacion = String.format("%s/auth/recuperar?token=%s", urlDominio, token);
            String cuerpoMensaje = String.format(
                "﻿<!DOCTYPE html> <!-- HTML del mensaje --> </html>",
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
            helper.setFrom("danitbp12@gmail.com");
            helper.setTo(emailDestino);
            helper.setSubject("Confirmación de cuenta AppCitasSAS");

            // Construcción del cuerpo del mensaje
            String urlDominio = "http://localhost:8080";
            String urlDeConfirmacion = String.format("%s/auth/confirmar-cuenta?token=%s", urlDominio, token);
            String cuerpoMensaje = String.format(
                "﻿<!DOCTYPE html> <!-- HTML del mensaje --> </html>",
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
