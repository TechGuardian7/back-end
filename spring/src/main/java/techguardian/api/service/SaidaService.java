package techguardian.api.service;

import java.util.List;

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
}
