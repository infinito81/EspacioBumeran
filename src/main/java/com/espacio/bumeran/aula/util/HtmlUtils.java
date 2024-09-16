package com.espacio.bumeran.aula.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.convert.out.html.HtmlExporterNG2;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class HtmlUtils {
    
    public static String convertDocxToHtml(String filePath) throws Exception {
        // Cargar el archivo .docx
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new File(filePath));

        // Configurar HTMLSettings
        HTMLSettings htmlSettings = new HTMLSettings();
        htmlSettings.setWmlPackage(wordMLPackage);

        // Usar StringWriter para capturar la salida HTML
        StringWriter stringWriter = new StringWriter();

        // Usar StreamResult para escribir el HTML
        StreamResult streamResult = new StreamResult(stringWriter);

        // Exportar a HTML usando HtmlExporterNG2
        HtmlExporterNG2 exporter = new HtmlExporterNG2();
        exporter.html(wordMLPackage, streamResult, htmlSettings);

        // Retornar el contenido HTML generado
        return stringWriter.toString();
    }
	
}
