package techguardian.api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import techguardian.api.dto.DadosEntrada;
import techguardian.api.entity.RegistroEntrada;
import techguardian.api.repository.EntradaRepository;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepo;

    public List<RegistroEntrada> buscarTodos() {
        return entradaRepo.findAll();
    }

    public void processarDadosEntrada(DadosEntrada dadosEntrada) {
        RegistroEntrada registroEntrada = new RegistroEntrada();
        registroEntrada.setDataEntrada(dadosEntrada.getDataEntrada());
        registroEntrada.setHoraEntrada(dadosEntrada.getHoraEntrada());
        registroEntrada.setQuantEntrada(dadosEntrada.getQuantEntrada());
        registroEntrada.setObsEntrada(dadosEntrada.getObsEntrada());

        entradaRepo.save(registroEntrada);
    }

    public void atualizarEntrada(Long id, DadosEntrada dadosEntrada) {
        Optional<RegistroEntrada> optionalEntrada = entradaRepo.findById(id);
        if (optionalEntrada.isPresent()) {
            RegistroEntrada entradaExistente = optionalEntrada.get();
            entradaExistente.setDataEntrada(dadosEntrada.getDataEntrada());
            entradaExistente.setHoraEntrada(dadosEntrada.getHoraEntrada());
            entradaExistente.setQuantEntrada(dadosEntrada.getQuantEntrada());
            entradaExistente.setObsEntrada(dadosEntrada.getObsEntrada());
            entradaRepo.save(entradaExistente);
        } else {
            throw new NoSuchElementException("Entrada com o ID " + id + " não encontrada");
        }
    }
}
