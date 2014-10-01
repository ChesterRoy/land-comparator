package com.roy.landcomparator.xlsx;

import com.roy.landcomparator.beans.Land;
import com.roy.landcomparator.gui.Comparator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileManager {

    public static Map<Double, Land> readExcelData() throws IOException {

        FileInputStream fis = null;
        Workbook workbook = null;
        Sheet sheet = null;
        Iterator<Row> rowIterator = null;
        
        Map<Double, Land> landsMap = new HashMap<>();
        
        int rowNumber = 0, cellNumber = 0;

        try {

            fis = new FileInputStream(Comparator.fileName);

            workbook = new XSSFWorkbook(fis);

            sheet = workbook.getSheet("Пообъектно");

            rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                rowNumber++;
                Row row = rowIterator.next();
                if (row.getRowNum() > 0) {
                    double CIDNUM = row.getCell(0).getNumericCellValue();
                    double NKMSTART = row.getCell(5).getNumericCellValue();
                    double NKMEND = row.getCell(6).getNumericCellValue();
                    int NKRPLANYEAR = (int) row.getCell(9).getNumericCellValue();
                    String CPIPENAME = row.getCell(18).getStringCellValue().trim();
                    Land land = new Land(NKMSTART, NKMEND, NKRPLANYEAR, CPIPENAME);
                    landsMap.put(CIDNUM, land);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException("Бида на строчке " + rowNumber);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return landsMap;
    }

    public static void setCellColoreIfUpdated(double CIDNUM, int cellIndex) throws IOException {
        FileInputStream fis = null;
        Workbook workbook = null;

        try {

            fis = new FileInputStream(Comparator.fileName);

            workbook = new XSSFWorkbook(fis);
            CellStyle newStyle = workbook.createCellStyle();
            newStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
            newStyle.setFillPattern(CellStyle.BORDER_MEDIUM);
            Sheet sheet = workbook.getSheet("Пообъектно");

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Cell cell = row.getCell(cellIndex);
                if (row.getRowNum() > 0 && row.getCell(0).getNumericCellValue() == CIDNUM) {
                    cell.setCellStyle(newStyle);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        FileOutputStream fileOut = new FileOutputStream(Comparator.fileName);
        workbook.write(fileOut);
        fileOut.close();
    }

    public static void setCellColoreIfNotAvailable(double CIDNUM) throws IOException {
        FileInputStream fis = null;
        Workbook workbook = null;

        try {

            fis = new FileInputStream(Comparator.fileName);

            workbook = new XSSFWorkbook(fis);
            CellStyle newStyle = workbook.createCellStyle();
            newStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
            newStyle.setFillPattern(CellStyle.BORDER_MEDIUM);
            Sheet sheet = workbook.getSheet("Пообъектно");

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Cell cell = row.getCell(0);
                if (row.getRowNum() > 0 && cell.getNumericCellValue() == CIDNUM) {
                    cell.setCellStyle(newStyle);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        FileOutputStream fileOut = new FileOutputStream(Comparator.fileName);
        workbook.write(fileOut);
        fileOut.close();
    }
}
