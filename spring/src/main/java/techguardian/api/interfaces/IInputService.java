package techguardian.api.interfaces;

import java.util.List;

import techguardian.api.dto.InputDTO;
import techguardian.api.entity.Input;

public interface IInputService {

    List<Input> findAll();
    void createInput(InputDTO dadosEntrada);
    void updateInput(Long id, InputDTO dadosEntrada);
    void deleteInput (Long id);

}
