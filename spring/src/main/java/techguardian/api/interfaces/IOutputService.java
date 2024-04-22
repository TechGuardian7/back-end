package techguardian.api.interfaces;

import java.util.List;

import techguardian.api.dto.OutputDTO;
import techguardian.api.entity.Output;

public interface IOutputService {

    List<Output> findAll();
    void createOutput(OutputDTO dadosSaida);
    void updateOutput(Long id, OutputDTO dadosSaida);
    void deletarSaida (Long id);
}
