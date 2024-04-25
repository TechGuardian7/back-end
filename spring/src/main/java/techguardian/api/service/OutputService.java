package techguardian.api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import techguardian.api.dto.OutputDTO;
import techguardian.api.entity.Output;
import techguardian.api.repository.OutputRepository;

@Service
public class OutputService {

    @Autowired
    private OutputRepository saidaRepo;

    public List<Output> findAll() {
        return saidaRepo.findAll();
    }

    public void createOutput(OutputDTO dadosSaida) {
        Output registroSaida = new Output();
        registroSaida.setDataSaida(dadosSaida.getDataSaida());
        registroSaida.setHoraSaida(dadosSaida.getHoraSaida());
        registroSaida.setQuantSaida(dadosSaida.getQuantSaida());
        registroSaida.setObsSaida(dadosSaida.getObsSaida());

        saidaRepo.save(registroSaida);
    }

    public void updateOutput(Long id, OutputDTO dadosSaida) {
        Optional<Output> optionalEntrada = saidaRepo.findById(id);
        if (optionalEntrada.isPresent()) {
            Output entradaExistente = optionalEntrada.get();
            entradaExistente.setDataSaida(dadosSaida.getDataSaida());
            entradaExistente.setHoraSaida(dadosSaida.getHoraSaida());
            entradaExistente.setQuantSaida(dadosSaida.getQuantSaida());
            entradaExistente.setObsSaida(dadosSaida.getObsSaida());
            saidaRepo.save(entradaExistente);
        } else {
            throw new NoSuchElementException("Saida com o ID " + id + " não encontrada");
        }
    }

    public void deleteOutput(Long id) {
        Optional<Output> optionalSaida = saidaRepo.findById(id);
        if (optionalSaida.isPresent()) {
            saidaRepo.deleteById(id);
        } else {
            throw new NoSuchElementException("Saida com o ID " + id + " não encontrada");
        }
    }
}
