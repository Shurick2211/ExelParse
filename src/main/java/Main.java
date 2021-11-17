import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
   static ArrayList<Row> rowsRez= new ArrayList<>();
   static List<Row> rows= new ArrayList<>();

    public static void main(String []args) throws IOException {
      /*  LinkParse.mainLink();
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheeT=workbook.createSheet("1");
        int i=0;
        for(String str:LinkParse.rez){
            Row row=sheeT.createRow(i++);
            Cell cell=row.createCell(14, CellType.valueOf("STRING"));
            cell.setCellValue(str);

        }
         workbook.write(new FileOutputStream("1.xlsx"));
        workbook.close();
*/
        XSSFSheet sheet=FilesWork.openWorkbook("1.xlsx").getSheet("1");
        Iterator<Row> rowIt =sheet.iterator();
        // rowsRez.add(sheet.getRow(0));
        //rowsRez.add(sheet.getRow(1));
        ArrayList<Row> rowsDobl= new ArrayList<>();
        while (rowIt.hasNext()){
            Row row= rowIt.next();
            if(row.getCell(4).getStringCellValue().startsWith("ORICO"))
                rows.add(row);
        }

       // addImText();
//addSt();
addKat();



      //  System.out.println("All="+rows.size());
       // System.out.println("ааа="+rows2.size());
       // System.out.println("Уникальных="+rowsRez.size());
       // System.out.println("Количество повторов="+rowsDobl.size());
       //FilesWork.saveWorkbook("rez.xlsx", (ArrayList<Row>) rowsRez);
      //  FilesWork.saveWorkbook("rezDbl.xlsx", rowsDobl);

    }

    private static void addSt() throws IOException {
        XSSFSheet sheet2=FilesWork.openWorkbook("2.xlsx").getSheet("Лист1");
        Iterator<Row> rowIt2 =sheet2.iterator();
        List<Row> rows2= new ArrayList<>();
        while (rowIt2.hasNext()){
            Row row= rowIt2.next();
            // if(row.getCell(4).getStringCellValue().startsWith("ORICO"))
            rows2.add(row);
        }
        String text;
        for (Row r:rows){
            if (!r.getCell(11).getStringCellValue().equals("")) rowsRez.add(r);
            String rex=r.getCell(4).getStringCellValue();
            for (Row r2:rows2){
                if (rex.equals(r2.getCell(0).getStringCellValue())) {
                    if (r.getCell(11).getStringCellValue().equals("")) {
                        text = r2.getCell(15).getStringCellValue();
                        if (!r2.getCell(16).getStringCellValue().equals(""))
                            text += ", " + r2.getCell(16).getStringCellValue();
                        if (!r2.getCell(17).getStringCellValue().equals(""))
                            text += ", " + r2.getCell(17).getStringCellValue();
                        r.getCell(11).setCellValue(text);
                        rowsRez.add(r);
                    }
                }

            }
        }
        FilesWork.saveWorkbook("rez.xlsx", (ArrayList<Row>) rowsRez);
    }


    private static void addImText() throws IOException {



        for (Row r:rows) {
            if (!r.getCell(14).getStringCellValue().equals("")) {
                //if (r.getCell(12).equals(""))

                // if(r.getCell(13).equals(""))
                r.createCell(0).setCellValue("");
                r.createCell(1).setCellValue("Доступен");
                r.createCell(2).setCellValue("");
                r.createCell(3).setCellValue("ORICO");
                r.createCell(4).setCellValue(
                        Parse.orikoImLinkImg(r.getCell(14).getStringCellValue())[2]);
                r.createCell(5).setCellValue(
                        Parse.orikoImLinkImg(r.getCell(14).getStringCellValue())[2].split(" ")[1]);
                r.createCell(6).setCellValue("");
                r.createCell(7).setCellValue("");
                r.createCell(8).setCellValue("");
                r.createCell(9).setCellValue("");
                r.createCell(10).setCellValue("");
                r.createCell(11).setCellValue("");
                r.createCell(12).setCellValue(
                        Parse.orikoImLinkImg(r.getCell(14).getStringCellValue())[0]);
                r.createCell(13).setCellValue(
                        Parse.orikoImLinkImg(r.getCell(14).getStringCellValue())[1]);
               System.out.println(r.getRowNum());


                rowsRez.add(r);
            }
        }

        FilesWork.saveWorkbook("rez.xlsx",  rowsRez);
    }

    private static void addKat() throws IOException {



        for (Row r:rows) {
            if (!r.getCell(14).getStringCellValue().equals("")) {
                //if (r.getCell(12).equals(""))

                // if(r.getCell(13).equals(""))

                r.createCell(2).setCellValue(
                Parse.orikoKat(r.getCell(14).getStringCellValue()));
                System.out.println(r.getRowNum());


                rowsRez.add(r);
            }
        }

        FilesWork.saveWorkbook("rez.xlsx",  rowsRez);
    }



}
