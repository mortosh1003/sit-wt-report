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
			// 1ファイルずつExcelのワークブックの読み込み
			// TODO:指定フォルダすべてのxlsxファイルを読むようにする
			Workbook book = new XSSFWorkbook(testFilePath);) {

			// シートの読み込み
			Sheet sheet = book.getSheet("TestScript");

			// 値読み込み
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

					// 1セルずつ出力
					System.out.println(rowIdx + ":" + colIdx + ":" + cellString);
					// TODO
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}