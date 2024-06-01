package appCitas.AppCitasSASv2.utils;

import java.util.Map;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import appCitas.AppCitasSASv2.dao.Informes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("informePacientePdf")
public class InformePdf extends AbstractPdfView {
	
	 @Override
	    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
	                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
	        Informes informe = (Informes) model.get("informe");
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	        String formattedDate = dateFormat.format(informe.getFchInforme().getTime()); // Aquí se formatea la fecha	        

	        PdfPTable table = new PdfPTable(3); // 3 columnas: Fecha, Nombre del Informe, Descripción
	        PdfPCell header1 = new PdfPCell(new Phrase("Fecha del Informe"));
	        PdfPCell header2 = new PdfPCell(new Phrase("Nombre del Informe"));
	        PdfPCell header3 = new PdfPCell(new Phrase("Descripción del Informe"));

	        table.addCell(header1);
	        table.addCell(header2);
	        table.addCell(header3);

	        table.addCell(new Phrase(formattedDate));
	        table.addCell(new Phrase(informe.getNombreInforme()));
	        table.addCell(new Phrase(informe.getDescInforme()));

	        document.add(table);
	    }
}
