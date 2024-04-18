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

import techguardian.api.dto.DadosSaida;
import techguardian.api.entity.RegistroSaida;
import techguardian.api.service.SaidaService;

@RestController
@CrossOrigin
public class SaidaController {

    @Autowired
    private SaidaService saidaService;

    @GetMapping("/registro/saida")
    public List<RegistroSaida> buscarTodos() {
        return saidaService.buscarTodos();
    }

    @PostMapping("/saida")
    public void receberDadosSaida(@RequestBody DadosSaida dadosSaida) {
        saidaService.processarDadosSaida(dadosSaida);
    }

    @PutMapping("/saida/{id}")
    public ResponseEntity<String> atualizarSaida(@PathVariable Long id, @RequestBody DadosSaida dadosSaida) {
        saidaService.atualizarSaida(id, dadosSaida);
        return ResponseEntity.ok("Saida atualizada com sucesso");
    }

    @DeleteMapping("/saida/{id}")
    public ResponseEntity<String> deletarSaida(@PathVariable Long id) {
        try {
            saidaService.deletarSaida(id);
            return ResponseEntity.ok("Saida com o ID " + id + " foi deletada com sucesso.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao deletar a saida.");
        }
    }
}
