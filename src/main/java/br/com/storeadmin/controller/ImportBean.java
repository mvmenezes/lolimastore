package br.com.storeadmin.controller;

import br.com.storeadmin.business.BOModel;
import br.com.storeadmin.business.CustomerBO;
import br.com.storeadmin.model.EnumModel.TypeImport;
import br.com.storeadmin.model.Store.Customer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ManagedBean(name="importBean")
@ViewScoped
public class ImportBean extends BeanModel implements Serializable
{

    private UploadedFile file;
    private StreamedContent fileDownload;
    private String typeImport;
    private BOModel boModel;


    public void upload ()
    {
        String relativePath="/resources/temp/";
        String folder = FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
        String fileName="";
        fileName = file.getFileName();
        folder+="\\"+fileName;
        if(file != null)
        {
            try
            {
                file.write(folder);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            List<String> sheetLines = importSheet(folder);
            sendLines(sheetLines);
            File f = new File(folder);

            f.delete();

        }else
        {
            addMsgError(MSG_ID_IMPORT,"Falha ao realizar o upload!");
        }
    }


    private void sendLines(List<String> sheetLines) {


        switch (typeImport)
        {
            case TypeImport.TYPE_CUSTOMER:
                boModel = new CustomerBO();
                break;
        }

        boModel.importSheet(sheetLines);
    }

    public List<String> importSheet (String file)
        {
        List<String> lines = new ArrayList<String>();
        try {
            String line = "";;
            FileInputStream excelFile = new FileInputStream(file);
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
                  //  if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        line += currentCell.getStringCellValue() + ";";
                  //  } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                   //     line += currentCell.getNumericCellValue() + ";";
                   // }

                }
                lines.add(line);
                System.out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Override
    public void init() {

        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/template/Import_Template.xlsx");
        fileDownload = new DefaultStreamedContent(stream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "Import_Customer_Template.xlsx");
    }

    @Override
    public void refreshStaticInformation() {

    }

    @Override
    public void lastValidation() {

    }


    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public StreamedContent getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(StreamedContent fileDownload) {
        this.fileDownload = fileDownload;
    }

    public void setTypeImport(String typeImport) {
        this.typeImport = typeImport;
    }

    public String getTypeImport() {
        return typeImport;
    }
}
