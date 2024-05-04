package techguardian.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import techguardian.api.entity.User;
import techguardian.api.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User createUser(User user) {
        if(user == null ||
                user.getNome() == null ||
                user.getNome().isBlank() ||
                user.getSenha() == null ||
                user.getSenha().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos!");
        }
        return userRepo.save(user);
    }

    public User updateUser(Long id, User user) {
        User existUser = userRepo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado - ID: " + id));
        if (user.getNome() != null) {
            existUser.setNome(user.getNome());
        }
        if (user.getSenha() != null) {
            existUser.setSenha(user.getSenha());
        }
        return userRepo.save(user);
    }

    public User deleteUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado - ID: " + id));
        userRepo.deleteById(id);
        return user;
    }
}
