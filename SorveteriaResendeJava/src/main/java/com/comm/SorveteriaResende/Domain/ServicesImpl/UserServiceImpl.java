package com.comm.SorveteriaResende.Domain.ServicesImpl;

import com.comm.SorveteriaResende.Data.Repositories.RolesRepository;
import com.comm.SorveteriaResende.Data.Repositories.UserRepository;
import com.comm.SorveteriaResende.Domain.DTOs.SignUpDTO;
import com.comm.SorveteriaResende.Domain.Models.Role;
import com.comm.SorveteriaResende.Domain.Models.User;
import com.comm.SorveteriaResende.Domain.Services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    RolesRepository _roleRepository;
    UserRepository _userRepository;

    public UserServiceImpl(UserRepository userRepository, RolesRepository rolesRepository) {
        _userRepository = userRepository;
        _roleRepository = rolesRepository;
    }

    @Override
    public List<User> listUsers() {
        return _userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return _userRepository.findById(userId);
    }

    @Override
    public Optional<User> findUserByNome(String nome) {
        return _userRepository.findByNome(nome);
    }

    @Override
    public Optional<User> findUserByCpf(String cpf) {
        return _userRepository.findByCpf(cpf);
    }

    @Override
    public Optional<User> findUserByEndereco(String endereco) {
        return _userRepository.findByEndereco(endereco);
    }

    @Override
    public Boolean signUp(SignUpDTO signUpDTO) {
        Optional<User> findUser = _userRepository.findByNome(signUpDTO.getNome());
        if (findUser.isPresent()) {
            throw new IllegalArgumentException("Este usuário já existe");
        }

        Optional<User> findEmail = _userRepository.findByEmail(signUpDTO.getEmail());
        if (findEmail.isPresent()) {
            throw new IllegalArgumentException("Este email já existe");
        }

        if (!signUpDTO.getSenha().equals(signUpDTO.getSenhaConfirmada())) {
            throw new IllegalArgumentException("A senha não confere com a confirmada!");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        List<Role> roles = new ArrayList<>();
        roles.add(_roleRepository.findBynome("ROLE_USER").orElse(null));

        User user = new User();
        user.setRoles(roles);
        user.setUsername(signUpDTO.getUsername());
        user.setNome(signUpDTO.getNome());
        user.setEmail(signUpDTO.getEmail());
        user.setCpf(signUpDTO.getCpf());
        user.setSenha(encoder.encode(signUpDTO.getSenha()));
        user.setEndereco(signUpDTO.getEndereco());

        User saveUser = _userRepository.save(user);

        return true;
    }

    @Override
    public Boolean updateUser(User user) {
        User findUser = _userRepository.findById(user.getId()).orElse(null);
        if (findUser == null) {
            throw new IllegalArgumentException("O usuário não existe");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        findUser.setNome(user.getNome());
        findUser.setEmail(user.getEmail());
        findUser.setCpf(user.getEmail());
        findUser.setSenha(encoder.encode(user.getEmail()));
        findUser.setUsername(user.getUsername());

        User updateUser = _userRepository.save(findUser);

        return true;
    }

    @Override
    public Boolean deleteUser(Long userId) {
        User findUser = _userRepository.findById(userId).orElse(null);
        if (findUser == null) {
            throw new IllegalArgumentException("O usuário não existe");
        }

        _userRepository.delete(findUser);

        return true;
    }


    @Override //login
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return _userRepository.findByUsername(username).orElse(null);
    }

}