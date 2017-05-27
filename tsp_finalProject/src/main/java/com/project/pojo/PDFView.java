package com.project.pojo;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PDFView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Font helvetica_18_blue = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.BLUE);
		List<Cart> cartList = (List<Cart>) model.get("cartList");

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] { 3.0f, 2.0f, 1.0f });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.WHITE);
		cell.setPadding(5);

		cell.setPhrase(new Phrase("Cart Id", helvetica_18_blue));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Total Items", helvetica_18_blue));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Total Price", helvetica_18_blue));
		table.addCell(cell);

		for (Cart c : cartList) {
			
			table.addCell(String.valueOf(c.getId()));
			table.addCell(String.valueOf(c.getTotalItems()));
			table.addCell(String.valueOf(c.getTotalPrice()));

		}
		document.add(table);
	}

}
