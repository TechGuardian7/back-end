package techguardian.api.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import techguardian.api.entity.Input;
import techguardian.api.entity.Output;
import techguardian.api.service.InputService;
import techguardian.api.service.OutputService;

@RestController
@RequestMapping("/registro/excel")
public class RecordController {

    @Autowired
    private InputService inputService;

    @Autowired
    private OutputService outputService;

    @GetMapping("/entrada")
    public ResponseEntity<byte[]> generateExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Registro_Entrada");

            List<Input> inputs = inputService.findAll();

            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Data de Entrada");
            headerRow.createCell(2).setCellValue("Hora de Entrada");
            headerRow.createCell(3).setCellValue("Quantidade de Entrada");
            headerRow.createCell(4).setCellValue("Observações");

            for (Cell cell : headerRow) {
                cell.setCellStyle(headerStyle);
            }

            int rowNum = 1;
            for (Input input : inputs) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(input.getId());
                row.createCell(1).setCellValue(input.getDataEntrada());
                row.createCell(2).setCellValue(input.getHoraEntrada());
                row.createCell(3).setCellValue(input.getQuantEntrada());
                row.createCell(4).setCellValue(input.getObsEntrada());

                for (Cell cell : row) {
                    cell.setCellStyle(headerStyle);
                }
            }

            int rowCount = sheet.getLastRowNum();
            int columnCount = headerRow.getLastCellNum();
            CellRangeAddress tableRange = new CellRangeAddress(0, rowCount, 0, columnCount - 1);
            sheet.setAutoFilter(tableRange);

            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("filename", "Registro_Entrada.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/saida")
    public ResponseEntity<byte[]> recordOutput() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Registro_Saida");

            List<Output> outputs = outputService.findAll();

            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Data da Saida");
            headerRow.createCell(1).setCellValue("Hora da Saida");
            headerRow.createCell(2).setCellValue("Quantidade");
            headerRow.createCell(3).setCellValue("Observação");

            for (Cell cell : headerRow) {
                cell.setCellStyle(headerStyle);
            }

            int rowNum = 1;
            for (Output output : outputs) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(output.getDataSaida());
                row.createCell(1).setCellValue(output.getHoraSaida());
                row.createCell(2).setCellValue(output.getQuantSaida());
                row.createCell(3).setCellValue(output.getObsSaida());

                for (Cell cell : row) {
                    cell.setCellStyle(headerStyle);
                }
            }

            int rowCount = sheet.getLastRowNum();
            int columnCount = headerRow.getLastCellNum();
            CellRangeAddress tableRange = new CellRangeAddress(0, rowCount, 0, columnCount - 1);
            sheet.setAutoFilter(tableRange);

            // Ajustando largura das colunas automaticamente
            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("filename", "Registro_Saida.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}