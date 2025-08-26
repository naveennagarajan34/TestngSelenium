package me.clockify.app.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public static List<String[]> readTimesheetData(String filePath) {
		List<String[]> data = new ArrayList<>();

		try {
			// Get the absolute path from project root
			String absolutePath = System.getProperty("user.dir") + File.separator + filePath;
			File file = new File(absolutePath);

			if (!file.exists()) {
				System.out.println("Excel file not found: " + absolutePath);
				return data;
			}

			FileInputStream fis = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(fis);

			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			// Skip header row
			if (rowIterator.hasNext())
				rowIterator.next();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				String[] rowData = new String[4]; // task, startTime, endTime, project
				boolean isEmptyRow = true;

				for (int i = 0; i < 4; i++) {
					Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					String cellValue = getCellValueAsString(cell);

					if (!cellValue.trim().isEmpty()) {
						isEmptyRow = false;
					}
					rowData[i] = cellValue;
				}

				if (!isEmptyRow) {
					data.add(rowData);
				}
			}

			workbook.close();
			fis.close();

		} catch (Exception e) {
			System.out.println("Error reading Excel file: " + e.getMessage());
			e.printStackTrace();
		}
		return data;
	}

	private static String getCellValueAsString(Cell cell) {
		if (cell == null || cell.getCellType() == CellType.BLANK) {
			return "";
		}

		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue().trim();
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getLocalDateTimeCellValue().toLocalTime().toString();
			} else {
				return String.valueOf((int) cell.getNumericCellValue());
			}
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		default:
			return "";
		}
	}
}
