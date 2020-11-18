package org.aaaa.FileHandlers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.aaaa.Enums.DatabasePath;

import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

public class FileHandlerReport extends FileHandler {
    public FileHandlerReport(String pathname) {
        super(pathname);
    }
    /*
    Fields
     */
    private List<List<String>> buffer;

    public void createPdf(String filename, String title, String database) throws Exception {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        //Setting font properties
        Font bold = new Font(Font.FontFamily.HELVETICA, 8f, Font.BOLD);
        Font normal = new Font(Font.FontFamily.HELVETICA, 8f, Font.NORMAL);
        //Creating table for header contents.
        PdfPTable headertable = new PdfPTable(1);
        headertable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        headertable.setWidthPercentage(100);
        PdfPTable table = new PdfPTable(2);
        float[] colWidths = { 45, 55 };
        table.setWidths(colWidths);
        //Fetch object from the web instead of local file.
        String imageUrl = "https://image.flaticon.com/icons/png/512/90/90417.png";
        Image image2 = Image.getInstance(new URL(imageUrl));
        image2.setWidthPercentage(10);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.addElement(image2);
        table.addCell(cell);
        //New LocalDateTime object of the current time.
        LocalDateTime currLDT = LocalDateTime.now();
        Chunk chunk1 = new Chunk("Date: ", normal);
        Phrase ph1 = new Phrase(chunk1);

        Chunk chunk2 = new Chunk(currLDT.toString(), bold);
        Phrase ph2 = new Phrase(chunk2);

        Chunk chunk3 = new Chunk("\nTitle: ", normal);
        Phrase ph3 = new Phrase(chunk3);

        Chunk chunk4 = new Chunk(title, bold);
        Phrase ph4 = new Phrase(chunk4);

        Paragraph ph = new Paragraph();
        ph.add(ph1);
        ph.add(ph2);
        ph.add(ph3);
        ph.add(ph4);

        table.addCell(ph);
        headertable.addCell(table);
        PdfContentByte canvas = writer.getDirectContent();
        canvas.saveState();
        canvas.setLineWidth((float) 10 / 10);
        canvas.moveTo(40, 773);
        canvas.lineTo(555, 773);
        canvas.stroke();
        document.add(headertable);
        canvas.restoreState();
        PdfPTable headertable1 = new PdfPTable(1);
        headertable1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        headertable1.setWidthPercentage(100);

        /*
         * Getting contents from the database
         */
        switch (database) {
            case "accounts" -> {
                FileHandlerAccount fh = new FileHandlerAccount(DatabasePath.Account.getName());
                buffer = fh.getContent(DatabasePath.Account.getDataLength());
            }
            case "users" -> {
                FileHandlerUser fh = new FileHandlerUser(DatabasePath.Staff.getName());
                buffer = fh.getContent(DatabasePath.Staff.getDataLength());
            }
            case "orders" -> {
                FileHandlerUser fh = new FileHandlerUser(DatabasePath.Order.getName());
                buffer = fh.getContent(DatabasePath.Order.getDataLength());
            }
            case "feedbacks" -> {
                FileHandlerUser fh = new FileHandlerUser(DatabasePath.Feedback.getName());
                buffer = fh.getContent(DatabasePath.Feedback.getDataLength());
            }
        }
        /*
          String builder object similar to string buffer... appending lines of database file to this object
         */
        StringBuilder sb = new StringBuilder();
        for(List<String> content : buffer){
            for(String lines : content){
                sb.append(lines);
                sb.append('\n');
            }
        }
        Paragraph contentParagraph = new Paragraph();
        contentParagraph.add(sb.toString());
        /*
         * Adding contents into the pdf
         */
        document.add(headertable1);
        document.add(contentParagraph);
        document.close();
    }
}
