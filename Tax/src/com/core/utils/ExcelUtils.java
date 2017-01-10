package com.core.utils;

import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.nsfw.user.entity.User;

public class ExcelUtils {
	public static void exportUserExcel(List<User> userList ,OutputStream outputStream) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);
			HSSFSheet sheet = workbook.createSheet("用户列表");
			sheet.addMergedRegion(cellRangeAddress);
			sheet.setDefaultColumnWidth(20);
			
			HSSFCellStyle style1 = createCellStyle(workbook, (short)16);
			HSSFRow row1 = sheet.createRow(0);
			HSSFCell cell1 = row1.createCell(0);
			cell1.setCellValue("用户列表");
			cell1.setCellStyle(style1);
			
			HSSFCellStyle style2 = createCellStyle(workbook, (short)13);
			String[] subtitle = {"用户名","账号","所属部门","性别","电子邮箱"};
			HSSFRow row2 = sheet.createRow(1);
			for(int i=0;i<subtitle.length;i++){
				HSSFCell cell2 = row2.createCell(i);
				cell2.setCellValue(subtitle[i]);
				cell2.setCellStyle(style2);
			}
			
			for(int i=0;i<userList.size();i++){
				HSSFRow row3 = sheet.createRow(i+2);
				row3.createCell(0).setCellValue(userList.get(i).getUserName());
				row3.createCell(1).setCellValue(userList.get(i).getAccount());
				row3.createCell(2).setCellValue(userList.get(i).getDept());
				row3.createCell(3).setCellValue(userList.get(i).isSex()?"男":"女");
				row3.createCell(4).setCellValue(userList.get(i).getEmail());
				}
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook,short fontSize){
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints(fontSize);
		style.setFont(font);
		return style;
	}

}
