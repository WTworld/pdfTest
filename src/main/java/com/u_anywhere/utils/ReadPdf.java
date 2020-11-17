package com.u_anywhere.utils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;

public class ReadPdf {

    public static void main(String[] args) throws IOException {

        try (PDDocument document = PDDocument.load(new File("C:\\Users\\zs\\Downloads\\DS1.pdf"))) {

            document.getClass();
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);
         // 设置是否排序
            PDFTextStripper tStripper = new PDFTextStripper();
            tStripper.setSortByPosition(true);
            // 设置起始页
            tStripper.setStartPage(1);
            // 设置结束页
            tStripper.setEndPage(Integer.MAX_VALUE);
            String pdfFileInText = tStripper.getText(document);
            //System.out.println("Text:" + st);
         
			// split by whitespace
            String lines[] = pdfFileInText.split("\\r\\n");
            System.out.println(pdfFileInText);
//            for (String line : lines) {
//                System.out.println(line);
//            }
//            if (!document.isEncrypted()) {
//
//                
//
//            }

        }

    }
}