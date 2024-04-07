package techguardian.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import techguardian.api.entity.RegistroEntrada;

public interface EntradaRepository extends JpaRepository<RegistroEntrada, Long> {

}
