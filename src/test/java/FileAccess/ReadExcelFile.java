package FileAccess;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReadExcelFile {
    public ReadExcelFile() {
    }

    public String[][] readExcelFile(String filepath, String sheetname) throws IOException{

        File file = new File(filepath);
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook newWorkBook = new XSSFWorkbook(inputStream);
        XSSFSheet newSheet = newWorkBook.getSheet(sheetname);
        int rowCount = newSheet.getLastRowNum() + 1; //- newSheet.getFirstRowNum();
        String val;
        String matriz[][] = new String[rowCount][8];
        DataFormatter formatter = new DataFormatter();
        for (int i=0; i<rowCount; i++){
            XSSFRow row = newSheet.getRow(i);
            for (int j=0; j<row.getLastCellNum()-1; j++){
                val=formatter.formatCellValue(row.getCell(j));
                matriz[i][j]=val;
            }
        }
        //System.out.println(matriz[7][0]);
        //System.out.println(rowCount);
        return matriz;
    }
}
