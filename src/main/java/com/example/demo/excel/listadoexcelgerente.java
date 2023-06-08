package com.example.demo.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.example.demo.entity.gerente;

@Component("listarExcelgerente")
public class listadoexcelgerente extends AbstractXlsxView {

	private static final String[] header = { "Id", "nombre", "apellido", "email" };

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		List<gerente> listadogerente = (List<gerente>) model.get("gerente");

		Sheet sheet = workbook.createSheet("Listadogerente");
		sheet.setFitToPage(true);

		// EstiloNegrillaCentrado
		Font font = workbook.createFont();
		font.setBold(true);
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);

		Row rowHeader = sheet.createRow(0);

		for (int i = 0; i < header.length; i++) {
			// sheet.setColumnWidth(i, 3000);
			Cell cell = rowHeader.createCell(i);
			cell.setCellValue(header[i]);
			cell.setCellStyle(style);
		}

		int rowCount = 1;

		// EstiloFecha
		CellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setDataFormat((short) 14);

		for (gerente gerente: listadogerente) {
			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(gerente.getId());
			row.createCell(1).setCellValue(gerente.getNombre());
			row.createCell(2).setCellValue(gerente.getApellido());

			Cell cell = row.createCell(4);
			cell.setCellValue(gerente.getEmail());
			cell.setCellStyle(dateStyle);

		}

		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(4, 3000);

		for (int i = 1; i <= 3; i++) {
			sheet.autoSizeColumn(i);
		}

		response.setHeader("Content-Disposition", "attachment; filename=listadoExcel.xlsx");

	}



	
	
}
