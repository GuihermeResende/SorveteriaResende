package com.comm.SorveteriaResende.Application.Controller;

import com.comm.SorveteriaResende.Domain.DTOs.SignUpDTO;
import com.comm.SorveteriaResende.Domain.Models.User;
import com.comm.SorveteriaResende.Domain.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService _userService;

    public UserController (UserService userService) {
        _userService = userService;
    }

    @GetMapping("/list-users")
    public ResponseEntity listUsers() {
        try {
            return ResponseEntity.ok(_userService.listUsers());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/find-by-id/{userId}")
    public ResponseEntity findUserById(@PathVariable("userId") Long userId) {
        try {
            return ResponseEntity.ok(_userService.findUserById(userId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/find-by-nome")
    public ResponseEntity findUserByNome(@RequestParam("nome") String nome) {
        try {
            return ResponseEntity.ok(_userService.findUserByNome(nome));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/find-by-cpf")
    public ResponseEntity findUserByCpf(@RequestParam("cpf") String cpf) {
        try {
            return ResponseEntity.ok(_userService.findUserByCpf(cpf));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/find-by-endereco")
    public ResponseEntity findUserByEndereco(@RequestParam("endereco") String endereco) {
        try {
            return ResponseEntity.ok(_userService.findUserByEndereco(endereco));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody SignUpDTO signUpDTO) {
        try {
            return ResponseEntity.ok(_userService.signUp(signUpDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/update-user")
    public ResponseEntity UpdateUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(_userService.updateUser(user));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity DeleteUser(@RequestParam("userId") Long userId) {
        try {
            return ResponseEntity.ok(_userService.deleteUser(userId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
