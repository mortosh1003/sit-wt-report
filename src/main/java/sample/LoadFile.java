package sample;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LoadFile {
	public LoadFile() {

		String testFilePath = "C:\\Users\\Mr.Guest\\Desktop\\excel\\SampleTestScript.xlsx";

		try (
			// 1�t�@�C������Excel�̃��[�N�u�b�N�̓ǂݍ���
			// TODO:�w��t�H���_���ׂĂ�xlsx�t�@�C����ǂނ悤�ɂ���
			Workbook book = new XSSFWorkbook(testFilePath);) {

			// �V�[�g�̓ǂݍ���
			Sheet sheet = book.getSheet("TestScript");

			// �l�ǂݍ���
			for (int rowIdx = sheet.getFirstRowNum(); rowIdx <= sheet.getLastRowNum(); rowIdx++) {
				Row row = sheet.getRow(rowIdx);

				if (row == null) {
					continue;
				}

				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

				for (int colIdx = row.getFirstCellNum(); colIdx < row.getLastCellNum(); colIdx++) {
					Cell cell = row.getCell(colIdx);
					String cellString = null;
					if (cell == null) {
						continue;
					}
					switch (cell.getCellType()) {
					case STRING:
						cellString = cell.getStringCellValue();
						break;
					case NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							Date date = cell.getDateCellValue();
							cellString = sdf1.format(date) + " " + sdf2.format(date);
						} else {
							cellString = Double.toString(cell.getNumericCellValue());
						}
						break;
					case FORMULA:
						cellString = cell.getCellFormula();
						break;
					default:
						break;
					}

					// 1�Z�����o��
					System.out.println(rowIdx + ":" + colIdx + ":" + cellString);
					// TODO
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}