package EduTechApp.example.demo.Service;

import EduTechApp.example.demo.Model.Certificado;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;

@Service
public class PdfCertificadoService {

    public void generarPdfCertificado(Certificado certificado, String rutaDestino) {
        try {
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(rutaDestino));
            document.open();

            // Agregar título
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 36, Font.BOLD);
            Paragraph title = new Paragraph("Certificado de Finalización", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Agregar contenido
            Font contentFont = new Font(Font.FontFamily.HELVETICA, 16);
            document.add(new Paragraph("\n\nPor la presente se certifica que:", contentFont));
            document.add(new Paragraph(certificado.getNombreEstudiante(),
                    new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD)));
            document.add(new Paragraph("\nHa completado exitosamente el curso:", contentFont));
            document.add(new Paragraph(certificado.getNombreCurso(),
                    new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD)));

            // Agregar fecha y firma
            document.add(new Paragraph("\n\nFecha: " + certificado.getFechaCertificado(), contentFont));
            document.add(new Paragraph("\nProfesor: " + certificado.getNombreProfesor(), contentFont));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}