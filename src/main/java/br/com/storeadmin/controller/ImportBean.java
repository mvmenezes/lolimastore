package br.com.storeadmin.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImportBean {



    public List<String> importSheet (String path)
    {
        List<String> lines = new ArrayList<String>();
        try {
            String line = "";
            FileInputStream excelFile = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                line = "";
                while (cellIterator.hasNext())
                {

                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        line += currentCell.getStringCellValue() + ";";
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        line += currentCell.getNumericCellValue() + ";";
                    }

                }
                lines.add(line);
                System.out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
