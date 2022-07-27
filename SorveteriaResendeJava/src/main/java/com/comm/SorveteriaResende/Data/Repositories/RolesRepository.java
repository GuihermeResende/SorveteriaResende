package com.comm.SorveteriaResende.Data.Repositories;

import com.comm.SorveteriaResende.Domain.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository <Role, Long> {

    Optional<Role> findBynome(String nome);

}
