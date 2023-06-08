package com.example.demo.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import com.example.demo.entity.asistente;

@Component("listar")
public class pdfasistente extends AbstractPdfView {

	private static final String[] header = { "Id", "propietario", "animal", "vacuna", "proveedor" };

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		List<asistente> listadoasistente = (List<asistente>) model.get("asistente");

		document.setPageSize(PageSize.LETTER.rotate());
		document.open();

		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda = null;
		celda = new PdfPCell(new Phrase("asistente"));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);

		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);

		PdfPTable tablaasistente = new PdfPTable(5);

		for (int i = 0; i < header.length; i++) {
			tablaasistente.addCell(header[i]);
		}

		listadoasistente.forEach(asistente -> {

			tablaasistente.addCell(asistente.getId().toString());
			tablaasistente.addCell(asistente.getPropietario());
			tablaasistente.addCell(asistente.getAnimal());
			tablaasistente.addCell(asistente.getVacuna());
			tablaasistente.addCell(asistente.getProveedor().toString());

		});

		document.add(tablaTitulo);
		document.add(tablaasistente);

	}

}
