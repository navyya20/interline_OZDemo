package jp.co.interlineOZDemo.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PDFController {

	
	
	public ArrayList<String> PDFtoJPG(String path, int JPGDPI) {
		File file = new File(path);
		PDDocument document = null;
		ArrayList<String> JPGPaths=new ArrayList<String>();
		try {
			document = PDDocument.load(file);
			int pageCount = document.getNumberOfPages();

			PDFRenderer pdfRenderer = new PDFRenderer(document);
			
			
			int pos = path .lastIndexOf(".");
			String JPGPath = path.substring(0, pos);
			
			//페이지를 JPG파일로 저장
			for(int i =0 ; i < pageCount ; i++) {
				BufferedImage imageObj = pdfRenderer.renderImageWithDPI(i, JPGDPI, ImageType.RGB);
				String newPath = JPGPath+i+".jpg";
				File outputfile = new File(newPath);
				ImageIO.write(imageObj, "jpg", outputfile);
				JPGPaths.add(newPath);
			}
			
		} catch (IOException e) {
			System.out.println("JPG로 변환중 오류 발생");
			e.printStackTrace();
		}finally {
			if (document != null) {
			    try {
					document.close();
				} catch (IOException e) {
					System.out.println("스트림close 오류.");
					e.printStackTrace();
				}
			}
		}
		return JPGPaths;
	}
	
	
	
	public void JPGtoPDF(ArrayList<String> JPGPaths , String outputFilePath)  {
		PDDocument document = new PDDocument();
		try {
		
			for (int i = 0 ; i<JPGPaths.size() ; i++) {
				File oneFile = new File(JPGPaths.get(i));
				InputStream inputStream = new FileInputStream(oneFile);
				BufferedImage bufferedImage = ImageIO.read(inputStream);
				float width = bufferedImage.getWidth();
				float height = bufferedImage.getHeight();
		
				// PDF 페이지 객체 1장 생성
				PDPage page = new PDPage(new PDRectangle(width, height));
				document.addPage(page);
		
		
		
				// PDF 문서 객체에 페이지 1장 그리기
				PDImageXObject pdImage = PDImageXObject.createFromFile(oneFile.getAbsolutePath(), document);
				PDPageContentStream contentStream = new PDPageContentStream(document, page);
				contentStream.drawImage(pdImage, 0, 0, width, height);
				// 1장 그릴 때마다 사용한 객체 닫기
				if (contentStream != null) {
				    contentStream.close();
				}
				if (inputStream != null) {
				    inputStream.close();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			document.save(outputFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (document != null) {
			    try {
					document.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void deleteFile(String path) {
		File file = new File(path); 
		if( file.exists() ){ 
			if(file.delete()){ 
				System.out.println("파일삭제 성공"); 
			}else{
				System.out.println("파일삭제 실패"); 
			} 
		}else{ 
			System.out.println("파일이 존재하지 않습니다."); 
		}
	}
	
}
