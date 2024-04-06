package techguardian.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import techguardian.api.entity.RegistroEntrada;
import techguardian.api.repository.EntradaRepository;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepo;

    public List<RegistroEntrada> buscarTodos() {
        return entradaRepo.findAll();
        }

    public RegistroEntrada novaEntrada(RegistroEntrada registro) {
        if(registro == null ||
        registro.getDataEntrada() == null ||
        registro.getHoraEntrada() == null) {
        throw new IllegalArgumentException("Registro Inválido!");
        }
        return entradaRepo.save(registro);
        }
}
