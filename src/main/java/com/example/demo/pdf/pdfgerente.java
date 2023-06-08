package com.example.demo.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.demo.entity.gerente;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("listargerente")
public class pdfgerente extends AbstractPdfView {

	private static final String[] header = { "Id", "nombre", "apellido", "email" };

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		List<gerente> listadogerente = (List<gerente>) model.get("gerente");

		document.setPageSize(PageSize.LETTER.rotate());
		document.open();

		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda = null;
		celda = new PdfPCell(new Phrase("gerente"));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);

		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);

		PdfPTable tablagerente = new PdfPTable(4);

		for (int i = 0; i < header.length; i++) {
			tablagerente.addCell(header[i]);
		}

		listadogerente.forEach(gerente-> {

			tablagerente.addCell(gerente.getId().toString());
			tablagerente.addCell(gerente.getNombre());
			tablagerente.addCell(gerente.getApellido());
			tablagerente.addCell(gerente.getEmail().toString());

		});

		document.add(tablaTitulo);
		document.add(tablagerente);

	}


}
