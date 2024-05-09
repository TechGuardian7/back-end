package techguardian.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import techguardian.api.entity.Output;
import techguardian.api.repository.OutputRepository;

@Service
public class OutputService {

    @Autowired
    private OutputRepository outRepo;

    public List<Output> findAll() {
        return outRepo.findAll();
    }

    public Output createOutput(Output createdOutput) {
        Output output = new Output();
        output.setDataSaida(createdOutput.getDataSaida());
        output.setHoraSaida(createdOutput.getHoraSaida());
        output.setQuantSaida(createdOutput.getQuantSaida());
        output.setStatus(createdOutput.getStatus());

        return outRepo.save(output);
    }

    public Output updateOutput(Long id, Output updatedOutput) {
        Output existOutput = outRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Saída não encontrada - ID: " + id));

    if (!ObjectUtils.isEmpty(updatedOutput.getDataSaida())) {
        existOutput.setDataSaida(updatedOutput.getDataSaida());    
    }

    if (!ObjectUtils.isEmpty(updatedOutput.getHoraSaida())) {
        existOutput.setHoraSaida(updatedOutput.getHoraSaida());
    }

    if (!ObjectUtils.isEmpty(updatedOutput.getQuantSaida())) {
        existOutput.setQuantSaida(updatedOutput.getQuantSaida());
    }

    if (!ObjectUtils.isEmpty(updatedOutput.getStatus())) {
        existOutput.setStatus(updatedOutput.getStatus());
    }

    return outRepo.save(existOutput);
    }

    public Output deleteOutput(Long id) {
        Output output = outRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Saída não encontrado - ID: " + id));
        outRepo.deleteById(id);
        return output;
    }
}
