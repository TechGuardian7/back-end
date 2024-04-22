package techguardian.api.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import techguardian.api.dto.OutputDTO;
import techguardian.api.entity.Output;
import techguardian.api.service.OutputService;

@RestController
@CrossOrigin
public class OutputController {

    @Autowired
    private OutputService saidaService;

    @GetMapping("/registro/saida")
    public List<Output> findAll() {
        return saidaService.findAll();
    }

    @PostMapping("/saida")
    public void createOutput(@RequestBody OutputDTO dadosSaida) {
        saidaService.createOutput(dadosSaida);
    }

    @PutMapping("/saida/{id}")
    public ResponseEntity<String> updateOutput(@PathVariable Long id, @RequestBody OutputDTO dadosSaida) {
        saidaService.updateOutput(id, dadosSaida);
        return ResponseEntity.ok("Saida atualizada com sucesso");
    }

    @DeleteMapping("/saida/{id}")
    public ResponseEntity<String> deleteOutput(@PathVariable Long id) {
        try {
            saidaService.deleteOutput(id);
            return ResponseEntity.ok("Saida com o ID " + id + " foi deletada com sucesso.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao deletar a saida.");
        }
    }
}
