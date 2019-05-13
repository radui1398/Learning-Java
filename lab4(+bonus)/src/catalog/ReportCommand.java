package catalog;

import items.Graph;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
/**
 * Commands that outputs a html report for the catalog.
 */
public class ReportCommand extends Command{

    @Override
    public void run(String[] args) throws IllegalArgumentException {
        if (args.length < 1){
            throw new IllegalArgumentException("At least 1 argument is required.");
        }
        if (!args[0].equals("report")){
            throw new IllegalArgumentException("Invalid command name");
        }

        String dir = "D:\\GitHub\\IdeaProjects\\lab4(+bonus)\\graph";
        String file, fullPath;

        switch (args[1]){
            case "html":
                file = "catalog_report." + args[1];

                fullPath = combine(dir, file);
                System.out.println(fullPath);

                try {
                    createHtmlReport(fullPath);
                }
                catch (Exception ignored){
                }

                break;
            case "pdf":
                file = "catalog_report." + args[1];
                fullPath = combine(dir, file);
                System.out.println(fullPath);

                try {
                    createPdfReport(fullPath);
                }
                catch (Exception ignored){
                }
                break;

            case "xls":
                file = "catalog_report." + args[1];
                fullPath = combine(dir, file);
                System.out.println(fullPath);

                try {
                    createExcelReport(fullPath);
                }
                catch (Exception ignored){
                }

            default:
                System.err.println("Format not supported");
        }
    }

    /**
     * Creates a html report for the current catalog"
     * @param fullPath - the path where the report is to be saved
     * @throws FileNotFoundException - the template not being found
     */
    private void createHtmlReport(String fullPath) throws FileNotFoundException {
        VelocityEngine ve = new VelocityEngine();

        try {
            ve.init();
        }
        catch (Exception e) {
            throw new FileNotFoundException(e.toString());
        }

        ArrayList list = new ArrayList();

        for (Graph graph : catalog.graphs){
            Map map = new HashMap();

            map.put("name", graph.getName());
            map.put("pngPath", graph.getPngPath());
            map.put("tgfPath", graph.getTgfPath());
            list.add( map );
        }

        VelocityContext context = new VelocityContext();
        context.put("itemList", list);

        try
        {
            Template t = ve.getTemplate( "template.vm" );

            StringWriter writer = new StringWriter();

            t.merge( context, writer );

            PrintWriter printWriter = new PrintWriter(fullPath);

            printWriter.write(writer.toString());
            printWriter.close();
        }
        catch( ResourceNotFoundException rnfe )
        {
            throw new FileNotFoundException(rnfe.toString());
        }
        catch( ParseErrorException pee )
        {
            throw new FileNotFoundException(pee.toString());
        }
        catch( MethodInvocationException mie )
        {
            throw new FileNotFoundException(mie.toString());
        }
        catch( Exception e )
        {
            throw new FileNotFoundException(e.toString());
        }
    }

    private void createPdfReport(String fullPath) throws FileNotFoundException {
        PDDocument doc = new PDDocument();
        try {
            PDPage page = new PDPage();
            doc.addPage(page);

            PDFont font = PDType1Font.HELVETICA_BOLD;

            PDPageContentStream contents = new PDPageContentStream(doc, page);
            contents.beginText();
            contents.setFont(font, 12);
            contents.newLineAtOffset(100, 700);
            contents.setLeading(14.5f);

            for  (Graph graph : catalog.graphs){
                contents.showText(graph.toString());
                contents.newLine();
            }

            contents.endText();
            contents.close();

            doc.save(fullPath);
        }
        catch (IOException e) {
            throw new FileNotFoundException(e.toString());
        }
        finally
        {
            try {
                doc.close();
            }
            catch (IOException e)
            {
                throw new FileNotFoundException(e.toString());
            }
        }
    }

    private void createExcelReport(String fullPath) throws FileNotFoundException, IOException{
        Workbook wb = new HSSFWorkbook();

        OutputStream fileOut = new FileOutputStream(fullPath);

        Sheet sheet = wb.createSheet("Graphs");

        Map<String, Object[]> data = new TreeMap<String, Object[]>();

        int pos = 2;
        data.put("1", new Object[] {"ID","NAME","TYPE","TGF PATH","PNG PATH","VERTICES","EDGES"});

        for (Graph graph : catalog.graphs) {
            data.put(Integer.toString(pos),
                    new Object[]{pos-1, graph.getName(), graph.getType(), graph.getTgfPath(),
                            graph.getPngPath(), graph.getVerticesCount(), graph.getEdgesCount()});
            pos++;
        }
        // Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            // this creates a new row in the sheet
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                // this line creates a cell in the next column of that row
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String)obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }

        wb.write(fileOut);
    }

    /**
     * Combines two given paths
     * @param path1 - first path
     * @param path2 - second path
     * @return
     */
    private String combine(String path1, String path2)
    {
        File file1 = new File(path1);
        File file2 = new File(file1, path2);
        return file2.getPath();
    }

    private static ReportCommand instance;
    public static Command getInstance() {
        if (instance == null){
            instance = new ReportCommand(Catalog.getInstance());
        }
        return instance;
    }
    private ReportCommand(Catalog catalog) {
        super(catalog);
    }
}
