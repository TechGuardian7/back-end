package techguardian.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import techguardian.api.dto.DadosEntrada;
import techguardian.api.entity.RegistroEntrada;
import techguardian.api.service.EntradaService;

@RestController
@CrossOrigin
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    @GetMapping("/registro/entrada")
    public List<RegistroEntrada> buscarTodos() {
        return entradaService.buscarTodos();
    }

    @PostMapping("/entrada")
    public void receberDadosEntrada(@RequestBody DadosEntrada dadosEntrada) {
        entradaService.processarDadosEntrada(dadosEntrada);
    }

    @PutMapping("/entrada/{id}")
    public ResponseEntity<String> atualizarEntrada(@PathVariable Long id, @RequestBody DadosEntrada dadosEntrada) {
        entradaService.atualizarEntrada(id, dadosEntrada);
        return ResponseEntity.ok("Entrada atualizada com sucesso");
    }

}