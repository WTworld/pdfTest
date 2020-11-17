package com.u_anywhere.utils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;

public class PDFReadUtil {
	public static String[] getLineArr(File file) throws IOException {
        try (PDDocument document = PDDocument.load(file)) {
            String[] lines = pdfDocumentGetDetail(document);
            return lines;
        }
    }
    public static String[] getLineArr(String filePath) throws IOException {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            String[] lines = pdfDocumentGetDetail(document);
            return lines;
//            if (!document.isEncrypted()) {
//            }
        }
    }
    public static String[] getLineArr(byte[] bytes) throws IOException {
        try (PDDocument document = PDDocument.load(bytes)) {
            String[] lines = pdfDocumentGetDetail(document);
            return lines;
        }
    }
	private static String[] pdfDocumentGetDetail(PDDocument document) throws IOException {
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
		String[] lines = pdfFileInText.split("\\r\\n");
		return lines;
	}
}