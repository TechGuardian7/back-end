package techguardian.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import techguardian.api.entity.RegistroSaida;
import techguardian.api.service.SaidaService;

@RestController
@RequestMapping(value = "/saida")
@CrossOrigin
public class SaidaController {

    @Autowired
    private SaidaService service;

    @GetMapping
    public List<RegistroSaida> buscarTodos() {
        return service.buscarTodos();
    }

    @PostMapping
    public RegistroSaida novaSaida(@RequestBody RegistroSaida saida) {
        return service.novaSaida(saida);
    }
}
