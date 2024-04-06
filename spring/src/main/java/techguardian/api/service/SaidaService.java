package techguardian.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import techguardian.api.entity.RegistroSaida;
import techguardian.api.repository.SaidaRepository;

@Service
public class SaidaService {

    @Autowired
    private SaidaRepository saidaRepo;

    public List<RegistroSaida> buscarTodos() {
        return saidaRepo.findAll();
        }

    public RegistroSaida novaSaida(RegistroSaida saida) {
        if(saida == null ||
        saida.getDataSaida() == null ||
        saida.getHoraSaida() == null) {
        throw new IllegalArgumentException("Registro Inválido!");
        }
        return saidaRepo.save(saida);
        }
}
