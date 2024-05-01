package techguardian.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import techguardian.api.entity.Input;

public interface InputRepository extends JpaRepository<Input, Long> {

}