package tn.telnet.workflow.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tn.telnet.workflow.models.EmployeeEvaluation;
import tn.telnet.workflow.models.EvaluationCompetencies;
import tn.telnet.workflow.models.User;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"Competency", "Self Rating", "Employee comments", "Appraiser Raiting", "Appraiser comments", "Weightage", "Points"};
    static String SHEET = "Tutorials";

    public static ByteArrayInputStream generateEmployeeEvaluationExcelFile(Optional<EmployeeEvaluation> evaluation, Optional<User> appraiser) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            sheet.setDefaultRowHeightInPoints(30);

            CellStyle headerStyle = createHeaderCellStyle(workbook);

            CellStyle firstColumnStyle = workbook.createCellStyle();
            firstColumnStyle.cloneStyleFrom(headerStyle);
            firstColumnStyle.setAlignment(HorizontalAlignment.LEFT);
            firstColumnStyle.setBorderTop(BorderStyle.MEDIUM);
            firstColumnStyle.setTopBorderColor(IndexedColors.WHITE.getIndex());
            Font font = workbook.createFont();
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 14);
            firstColumnStyle.setFont(font);

            // Add employee name row
            Row row1 = sheet.createRow(0);
            Row row2 = sheet.createRow(1);
            Row row3 = sheet.createRow(2);
            Row row4 = sheet.createRow(3);
            Row row5 = sheet.createRow(4);


            Cell employeeNameCell = row1.createCell(0);
            Cell employeeProfileCell = row2.createCell(0);
            Cell appraiserNameCell = row3.createCell(0);
            Cell employeeSignatureDateCell = row4.createCell(0);
            Cell appraiserSignatureDateCell = row5.createCell(0);


            employeeNameCell.setCellValue("Employee Name: " + evaluation.get().getUser().getFirstname() + " " + evaluation.get().getUser().getLastname());
            appraiserNameCell.setCellValue("Appraiser Name: " + appraiser.get().getFirstname() + " " + appraiser.get().getLastname());
            employeeProfileCell.setCellValue("Employee Profile: " + evaluation.get().getUser().getProfile().getName());
            employeeSignatureDateCell.setCellValue("Employee Signature Date: " + evaluation.get().getDateEmployeeSignature());
            appraiserSignatureDateCell.setCellValue("Appraiser Signature Date: " + evaluation.get().getDateAppraiserSignature());


            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, HEADERs.length - 1)); // Merge cells for employee name
            CellStyle nameCellStyle = workbook.createCellStyle();
            Font nameFont = workbook.createFont();
            nameFont.setBold(true);
            nameFont.setFontHeightInPoints((short) 14);
            nameCellStyle.setFont(nameFont);
            employeeNameCell.setCellStyle(nameCellStyle);
            appraiserNameCell.setCellStyle(nameCellStyle);
            employeeProfileCell.setCellStyle(nameCellStyle);
            employeeSignatureDateCell.setCellStyle(nameCellStyle);
            appraiserSignatureDateCell.setCellStyle(nameCellStyle);


            // Header
            Row headerRow = sheet.createRow(7);
            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
                cell.setCellStyle(headerStyle);
                if (col == 0) {
                    sheet.setColumnWidth(col, 23000);
                } else {
                    sheet.setColumnWidth(col, 6000);
                }
            }

            if (evaluation.isPresent()) {
                int rowIdx = 8;

                CellStyle cellStyle = workbook.createCellStyle();
                Font fontCell = workbook.createFont();
                fontCell.setFontHeightInPoints((short) 14);
                cellStyle.setFont(fontCell);
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                for (EvaluationCompetencies comp : evaluation.get().getEvaluationCompetencies()) {
                    Row row = sheet.createRow(rowIdx++);


                    Cell cell0 = row.createCell(0);
                    cell0.setCellValue(comp.getCompetency().getName());
                    cell0.setCellStyle(firstColumnStyle);


                    Cell cell1 = row.createCell(1);
                    cell1.setCellValue(comp.getEmployee_mark());
                    cell1.setCellStyle(cellStyle);

                    Cell cell2 = row.createCell(2);
                    cell2.setCellValue(comp.getEmployee_comment());
                    cell2.setCellStyle(cellStyle);

                    Cell cell3 = row.createCell(3);
                    cell3.setCellValue(comp.getAppraiser_mark());
                    cell3.setCellStyle(cellStyle);

                    Cell cell4 = row.createCell(4);
                    cell4.setCellValue(comp.getAppraiser_comment());
                    cell4.setCellStyle(cellStyle);

                    Cell cell5 = row.createCell(5);
                    cell5.setCellValue(comp.getEmployee_mark());
                    cell5.setCellStyle(cellStyle);

                    Cell cell6 = row.createCell(6);
                    cell6.setCellValue(comp.getEmployee_mark());
                    cell6.setCellStyle(cellStyle);

                }
                double totalPpoints = evaluation.get().getFinal_mark();
                String evaluation_objectives = evaluation.get().getObjectives();
                if (evaluation_objectives == "") {
                    evaluation_objectives = "No objectives Found";
                }

                CellStyle totalLabelCellStyle = workbook.createCellStyle();
                totalLabelCellStyle.setAlignment(HorizontalAlignment.RIGHT);
                totalLabelCellStyle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
                totalLabelCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                Font totalLabelCellFont = workbook.createFont();
                totalLabelCellFont.setColor(IndexedColors.BLACK.getIndex());
                totalLabelCellFont.setBold(true);
                totalLabelCellFont.setFontHeightInPoints((short) 14);
                totalLabelCellStyle.setFont(totalLabelCellFont);

                CellStyle objectivesCellStyle = workbook.createCellStyle();
                objectivesCellStyle.setAlignment(HorizontalAlignment.LEFT);
                Font objectivesLabelCellFont = workbook.createFont();
                objectivesLabelCellFont.setColor(IndexedColors.BLACK.getIndex());
                objectivesLabelCellFont.setBold(true);
                objectivesLabelCellFont.setFontHeightInPoints((short) 14);
                objectivesCellStyle.setFont(objectivesLabelCellFont);

                // Add Total Points row
                Row totalPointsRow = sheet.createRow(rowIdx);
                Cell totalLabelCell = totalPointsRow.createCell(0);
                totalLabelCell.setCellValue("Total Points :" + totalPpoints);
                totalLabelCell.setCellStyle(totalLabelCellStyle);

                Row ojectivesRow = sheet.createRow(20);
                Cell ojectivesLabelCell = ojectivesRow.createCell(0);
                ojectivesLabelCell.setCellValue("Ojectives :" + evaluation_objectives);
                ojectivesLabelCell.setCellStyle(objectivesCellStyle);

                /*Cell totalPointsCell = totalPointsRow.createCell(6);
                totalPointsCell.setCellValue("Total Points : "+12);
                totalPointsCell.setCellStyle(totalLabelCellStyle);*/

                sheet.addMergedRegion(new CellRangeAddress(rowIdx, rowIdx, 0, 6));
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    private static CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setTopBorderColor(IndexedColors.WHITE.getIndex());
        style.setBottomBorderColor(IndexedColors.WHITE.getIndex());

        Font font = workbook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);
        return style;
    }
}
