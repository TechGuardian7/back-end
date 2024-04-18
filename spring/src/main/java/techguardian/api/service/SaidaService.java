package techguardian.api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import techguardian.api.dto.DadosSaida;
import techguardian.api.entity.RegistroSaida;
import techguardian.api.repository.SaidaRepository;

@Service
public class SaidaService {

    @Autowired
    private SaidaRepository saidaRepo;

    public List<RegistroSaida> buscarTodos() {
        return saidaRepo.findAll();
    }

    public void processarDadosSaida(DadosSaida dadosSaida) {
        RegistroSaida registroSaida = new RegistroSaida();
        registroSaida.setDataSaida(dadosSaida.getDataSaida());
        registroSaida.setHoraSaida(dadosSaida.getHoraSaida());
        registroSaida.setQuantSaida(dadosSaida.getQuantSaida());
        registroSaida.setObsSaida(dadosSaida.getObsSaida());

        saidaRepo.save(registroSaida);
    }

    public void atualizarSaida(Long id, DadosSaida dadosSaida) {
        Optional<RegistroSaida> optionalEntrada = saidaRepo.findById(id);
        if (optionalEntrada.isPresent()) {
            RegistroSaida entradaExistente = optionalEntrada.get();
            entradaExistente.setDataSaida(dadosSaida.getDataSaida());
            entradaExistente.setHoraSaida(dadosSaida.getHoraSaida());
            entradaExistente.setQuantSaida(dadosSaida.getQuantSaida());
            entradaExistente.setObsSaida(dadosSaida.getObsSaida());
            saidaRepo.save(entradaExistente);
        } else {
            throw new NoSuchElementException("Saida com o ID " + id + " não encontrada");
        }
    }

    public void deletarSaida(Long id) {
        Optional<RegistroSaida> optionalSaida = saidaRepo.findById(id);
        if (optionalSaida.isPresent()) {
            saidaRepo.deleteById(id);
        } else {
            throw new NoSuchElementException("Saida com o ID " + id + " não encontrada");
        }
    }
}
