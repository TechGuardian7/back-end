package techguardian.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
