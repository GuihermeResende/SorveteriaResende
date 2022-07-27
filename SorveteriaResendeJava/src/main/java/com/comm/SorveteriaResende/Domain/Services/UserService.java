package com.comm.SorveteriaResende.Domain.Services;

import com.comm.SorveteriaResende.Domain.DTOs.SignUpDTO;
import com.comm.SorveteriaResende.Domain.Models.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> listUsers();
    Optional<User> findUserById(Long userId);
    Optional<User> findUserByNome(String nome);
    Optional<User> findUserByCpf(String cpf);
    Optional<User> findUserByEndereco(String endereco);
    Boolean signUp(SignUpDTO signUpDTO);
    Boolean updateUser(User user);
    Boolean deleteUser(Long userId);

}
