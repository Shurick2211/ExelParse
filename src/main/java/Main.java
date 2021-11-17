import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
   static ArrayList<Row> rowsRez= new ArrayList<>();
   static List<Row> rows= new ArrayList<>();

    public static void main(String []args) throws IOException {

        LinkParse.mainLink();


        XSSFSheet sheet=FilesWork.openWorkbook("1.xlsx").getSheet("Список товаров");
        Iterator<Row> rowIt =sheet.iterator();
        // rowsRez.add(sheet.getRow(0));
        //rowsRez.add(sheet.getRow(1));
        ArrayList<Row> rowsDobl= new ArrayList<>();
        while (rowIt.hasNext()){
            Row row= rowIt.next();
            if(row.getCell(4).getStringCellValue().startsWith("ORICO"))
                rows.add(row);
        }

        addImText();
/*
        for (Row r:rows){
            int i=0;
            String rex=r.getCell(4).getStringCellValue();

            for (Row rr:rows){

                if (rex.equals(rr.getCell(4).getStringCellValue()))i++;
                if(i==1) rowsRez.add(rr);
                if(i>1) {

                    rowsDobl.add(rr);}
               // System.out.println("i="+i+" #="+rr.getRowNum());

            }

        }

*/
      //  System.out.println("All="+rows.size());
       // System.out.println("ааа="+rows2.size());
       // System.out.println("Уникальных="+rowsRez.size());
       // System.out.println("Количество повторов="+rowsDobl.size());
       //FilesWork.saveWorkbook("rez.xlsx", (ArrayList<Row>) rowsRez);
      //  FilesWork.saveWorkbook("rezDbl.xlsx", rowsDobl);

    }

    private void addSt() throws IOException {
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
                    r.getCell(12).setCellValue(
                            Parse.orikoImLinkImg(r.getCell(14).getStringCellValue())[0]);
                // if(r.getCell(13).equals(""))
               r.getCell(13).setCellValue(
                        Parse.orikoImLinkImg(r.getCell(14).getStringCellValue())[1]);
System.out.println(r.getRowNum());
                rowsRez.add(r);
            }
        }

        FilesWork.saveWorkbook("rez.xlsx",  rowsRez);
    }


}
