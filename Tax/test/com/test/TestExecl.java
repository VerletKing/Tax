package com.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestExecl {

	@Test
	public void testWrite2Execl03() throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("hello");
		HSSFRow row = sheet.createRow(2);
		HSSFCell cell = row.createCell(2);
		cell.setCellValue("Hello word!");
		
		FileOutputStream outputStream = new FileOutputStream("F://����.xls");
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
	}
	
	@Test
	public void testRead2Execl03() throws Exception {
		FileInputStream inputStream = new FileInputStream("F://����.xls");
		
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFRow row = sheet.getRow(2);
		HSSFCell cell = row.getCell(2);
		System.out.println(cell.getStringCellValue());
		
		workbook.close();
		inputStream.close();
	}

	@Test
	public void testWrite2Execl07() throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("hello");
		XSSFRow row = sheet.createRow(2);
		XSSFCell cell = row.createCell(2);
		cell.setCellValue("Hello word!");
		
		FileOutputStream outputStream = new FileOutputStream("F://����.xlsx");
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
	}
	
	@Test
	public void testRead2Execl07() throws Exception {
		FileInputStream inputStream = new FileInputStream("F://����.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(2);
		XSSFCell cell = row.getCell(2);
		System.out.println(cell.getStringCellValue());
		
		workbook.close();
		inputStream.close();
	}

	@Test
	public void testRead2Execl07And03() throws Exception {
		String path = "F:\\����.xlsx";
		FileInputStream inputStream = new FileInputStream(path);
		
		if(path.matches("^.*\\.(?i)((xls)|(xlsx))$")){
			boolean is03Execl = true;
			if(path.matches("^.*\\.(?i)(xlsx)$")){
				is03Execl=false;
			}
			Workbook workbook = is03Execl ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(1);
			Cell cell = row.getCell(1);
			System.out.println(cell.getStringCellValue());
			workbook.close();
		}
		
		inputStream.close();
	}

	@Test
	public void TestWriteExcel03And07() throws Exception{
		
		String path = "F:\\����.xlsx";
		if (path.matches("^.*\\.(?i)((xls)|(xlsx))$")) {
			boolean is03Execl = true;
			if (path.matches("^.*\\.(?i)(xlsx)$")) {
				is03Execl = false;
			}
			Workbook workbook = is03Execl ? new HSSFWorkbook() : new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("one");
			Row row = sheet.createRow(1);
			Cell cell = row.createCell(1);
			cell.setCellValue("one");
			FileOutputStream outputStream = new FileOutputStream(path);
			workbook.write(outputStream);
			outputStream.close();
			workbook.close();
		}
	}
	
	@Test
	public void testStyle() throws Exception{
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//�����ϲ���Ԫ��
		CellRangeAddress cellRangeAddress = new CellRangeAddress(2, 2, 2, 4);
		
		//������Ԫ�����ʽ
		HSSFCellStyle style = workbook.createCellStyle();
		//����ˮƽ����
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//���ô�ֱ����
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//���ñ������ģʽ
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		//������䱳��ɫ
		style.setFillBackgroundColor(HSSFColor.YELLOW.index);
		//�������ǰ��ɫ
		style.setFillForegroundColor(HSSFColor.RED.index);
		
		HSSFFont font = workbook.createFont();
		//���üӴ�����
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//���������С
		font.setFontHeightInPoints((short) 16);
		//���뵽��ʽ��
		style.setFont(font);
		
		HSSFSheet sheet = workbook.createSheet("sheet");
		//�ϲ���Ԫ�����õ�sheet��
		sheet.addMergedRegion(cellRangeAddress);
		HSSFRow row = sheet.createRow(2);
		HSSFCell cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("Hello Word!");
		
		FileOutputStream outputStream = new FileOutputStream("F://����.xls");
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
	}
}
