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

import techguardian.api.dto.InputDTO;
import techguardian.api.entity.Input;
import techguardian.api.service.InputService;

@RestController
@CrossOrigin
public class InputController {

    @Autowired
    private InputService entradaService;

    @GetMapping("/registro/entrada")
    public List<Input> findAll() {
        return entradaService.findAll();
    }

    @PostMapping("/entrada")
    public void createInput(@RequestBody InputDTO dadosEntrada) {
        entradaService.createInput(dadosEntrada);
    }

    @PutMapping("/entrada/{id}")
    public ResponseEntity<String> updateInput(@PathVariable Long id, @RequestBody InputDTO dadosEntrada) {
        entradaService.updateInput(id, dadosEntrada);
        return ResponseEntity.ok("Entrada atualizada com sucesso");
    }

    @DeleteMapping("/entrada/{id}")
    public ResponseEntity<String> deleteInput(@PathVariable Long id) {
        try {
            entradaService.deleteInput(id);
            return ResponseEntity.ok("Entrada com o ID " + id + " foi deletada com sucesso.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao deletar a entrada.");
        }
    }

}