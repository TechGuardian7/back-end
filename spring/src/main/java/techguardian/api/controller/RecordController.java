package techguardian.api.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
    public ResponseEntity<byte[]> recordInput() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Registro_Entrada");

            List<Input> inputs = inputService.findAll();

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Data de Entrada");
            headerRow.createCell(1).setCellValue("Hora de Entrada");
            headerRow.createCell(2).setCellValue("Quantidade");
            headerRow.createCell(3).setCellValue("Observação");

            int rowNum = 1;
            for (Input input : inputs) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(input.getDataEntrada());
                row.createCell(1).setCellValue(input.getHoraEntrada());
                row.createCell(2).setCellValue(input.getQuantEntrada());
                row.createCell(3).setCellValue(input.getObsEntrada());
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

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Data da Saida");
            headerRow.createCell(1).setCellValue("Hora da Saida");
            headerRow.createCell(2).setCellValue("Quantidade");
            headerRow.createCell(3).setCellValue("Observação");

            int rowNum = 1;
            for (Output output : outputs) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(output.getDataSaida());
                row.createCell(1).setCellValue(output.getHoraSaida());
                row.createCell(2).setCellValue(output.getQuantSaida());
                row.createCell(3).setCellValue(output.getObsSaida());
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