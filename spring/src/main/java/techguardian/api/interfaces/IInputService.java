package techguardian.api.interfaces;

import java.util.List;

import techguardian.api.entity.Input;

public interface IInputService {

    List<Input> findAll();
    Input createInput(Input createInput);
    Input updateInput(Long id, Input updateInput);
    Input deleteInput (Long id);

}
