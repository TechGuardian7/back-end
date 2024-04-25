package techguardian.api.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registro/excel")
public class RecordController {

    @GetMapping("/entrada")
    public ResponseEntity<byte[]> recordInput() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Registro_Entrada");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Data de Entrada");
            headerRow.createCell(1).setCellValue("Hora de Entrada");
            headerRow.createCell(2).setCellValue("Quantidade");
            headerRow.createCell(3).setCellValue("Observação");

            Row dataRow1 = sheet.createRow(1);
            dataRow1.createCell(0).setCellValue("19/04/2024");
            dataRow1.createCell(1).setCellValue("14:15:23");
            dataRow1.createCell(2).setCellValue(1);
            dataRow1.createCell(3).setCellValue("Registrado a Entrada");


            Row dataRow2 = sheet.createRow(2);
            dataRow2.createCell(0).setCellValue("20/04/2024");
            dataRow2.createCell(1).setCellValue("15:23:47");
            dataRow2.createCell(2).setCellValue(1);
            dataRow2.createCell(3).setCellValue("Registrado a Entrada");

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

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Data da Saida");
            headerRow.createCell(1).setCellValue("Hora da Saida");
            headerRow.createCell(2).setCellValue("Quantidade");
            headerRow.createCell(3).setCellValue("Observação");

            Row dataRow1 = sheet.createRow(1);
            dataRow1.createCell(0).setCellValue("19/04/2024");
            dataRow1.createCell(1).setCellValue("14:20:12");
            dataRow1.createCell(2).setCellValue(1);
            dataRow1.createCell(3).setCellValue("Registrado a Saida");


            Row dataRow2 = sheet.createRow(2);
            dataRow2.createCell(0).setCellValue("20/04/2024");
            dataRow2.createCell(1).setCellValue("15:30:00");
            dataRow2.createCell(2).setCellValue(1);
            dataRow2.createCell(3).setCellValue("Registrado a Saida");

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
