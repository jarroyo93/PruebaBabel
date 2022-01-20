package FileAccess;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExcelFile {
    public WriteExcelFile() {
    }

    public void writeCellValue(String filePath, String sheetName, int rowNumber, int cellNumber, String resultText) throws IOException {
        File file = new File(filePath);

        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook newWorkBook = new XSSFWorkbook(inputStream);

        XSSFSheet newSheet = newWorkBook.getSheet(sheetName);

        XSSFRow row = newSheet.getRow(rowNumber);

        XSSFCell firstCell = row.getCell(cellNumber-1);

        XSSFCell nextCell = row.createCell((cellNumber));

        nextCell.setCellValue(resultText);

        inputStream.close();

        FileOutputStream outStream = new FileOutputStream(file);

        newWorkBook.write(outStream);

        outStream.close();

    }
}
