package techguardian.api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import techguardian.api.dto.InputDTO;
import techguardian.api.entity.Input;
import techguardian.api.repository.InputRepository;

@Service
public class InputService {

    @Autowired
    private InputRepository entradaRepo;

    public List<Input> findAll() {
        return entradaRepo.findAll();
    }

    public void createInput(InputDTO dadosEntrada) {
        Input registroEntrada = new Input();
        registroEntrada.setDataEntrada(dadosEntrada.getDataEntrada());
        registroEntrada.setHoraEntrada(dadosEntrada.getHoraEntrada());
        registroEntrada.setQuantEntrada(dadosEntrada.getQuantEntrada());
        registroEntrada.setObsEntrada(dadosEntrada.getObsEntrada());

        entradaRepo.save(registroEntrada);
    }

    public void updateInput(Long id, InputDTO dadosEntrada) {
        Optional<Input> optionalEntrada = entradaRepo.findById(id);
        if (optionalEntrada.isPresent()) {
            Input entradaExistente = optionalEntrada.get();
            entradaExistente.setDataEntrada(dadosEntrada.getDataEntrada());
            entradaExistente.setHoraEntrada(dadosEntrada.getHoraEntrada());
            entradaExistente.setQuantEntrada(dadosEntrada.getQuantEntrada());
            entradaExistente.setObsEntrada(dadosEntrada.getObsEntrada());
            entradaRepo.save(entradaExistente);
        } else {
            throw new NoSuchElementException("Entrada com o ID " + id + " não encontrada");
        }
    }

    public void deleteInput(Long id) {
        Optional<Input> optionalEntrada = entradaRepo.findById(id);
        if (optionalEntrada.isPresent()) {
            entradaRepo.deleteById(id);
        } else {
            throw new NoSuchElementException("Entrada com o ID " + id + " não encontrada");
        }
    }
}
