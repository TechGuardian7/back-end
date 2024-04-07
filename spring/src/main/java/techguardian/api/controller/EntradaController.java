package techguardian.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import techguardian.api.entity.RegistroEntrada;
import techguardian.api.service.EntradaService;

@RestController
@RequestMapping(value = "/entrada")
@CrossOrigin
public class EntradaController {

    @Autowired
    private EntradaService service;

    @GetMapping
    public List<RegistroEntrada> buscarTodos() {
        return service.buscarTodos();
    }

    @PostMapping
    public RegistroEntrada novaEntrada(@RequestBody RegistroEntrada entrada) {
        return service.novaEntrada(entrada);
    }
}
