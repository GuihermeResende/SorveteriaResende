package com.comm.SorveteriaResende.Data.Repositories;

import com.comm.SorveteriaResende.Domain.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNome(String nome);
    Optional<User> findByCpf(String cpf);
    Optional<User> findByEndereco(String endereco);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

}
