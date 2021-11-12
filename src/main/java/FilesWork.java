import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class FilesWork {
    public static XSSFWorkbook openWorkbook(String nameFile) throws IOException {

        return new XSSFWorkbook(new FileInputStream(nameFile));
    }

    public static void saveWorkbook(String fileName, ArrayList<Row> rows) throws IOException {
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet=workbook.createSheet("1");

        for(Row r:rows){
        Row row=sheet.createRow(r.getRowNum());
            Iterator<Cell> cellIt=r.iterator();
            int ic=0;
            while (cellIt.hasNext()){
                Cell c=cellIt.next();
                Cell cell=row.createCell(ic, c.getCellType());

                ic++;

                switch (c.getCellType()){
                    case _NONE:
                        cell.setCellValue("");
                        break;

                    case BLANK:
                        cell.setBlank();
                        break;
                    case FORMULA:
                        cell.setCellFormula(c.getCellFormula());
                        break;
                    case NUMERIC:
                        cell.setCellValue(c.getNumericCellValue());
                        break;
                    case STRING:
                        cell.setCellValue(c.getStringCellValue());
                        break;
                    case ERROR:
                        cell.setCellValue(c.getErrorCellValue());
                        break;

                }
            }
        }

        workbook.write(new FileOutputStream(fileName));
        workbook.close();
    }
}
