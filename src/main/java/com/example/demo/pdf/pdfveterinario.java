package com.example.demo.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.demo.entity.veterinario;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("listarveterinario")
public class pdfveterinario extends AbstractPdfView {

	private static final String[] header = { "Id", "daignostico"};

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		List<veterinario> listadoveterinario= (List<veterinario>) model.get("veterinario");

		document.setPageSize(PageSize.LETTER.rotate());
		document.open();

		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda = null;
		celda = new PdfPCell(new Phrase("veterinario"));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);

		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);

		PdfPTable tablaveterinario = new PdfPTable(2);

		for (int i = 0; i < header.length; i++) {
			tablaveterinario.addCell(header[i]);
		}

		listadoveterinario.forEach(asistente -> {

			tablaveterinario.addCell(asistente.getId().toString());
			
			tablaveterinario.addCell(asistente.getDiagnostico().toString());

		});

		document.add(tablaTitulo);
		document.add(tablaveterinario);

	}

}
