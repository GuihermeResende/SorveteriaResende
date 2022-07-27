package com.comm.SorveteriaResende.Application.Seeders;

import com.comm.SorveteriaResende.Data.Repositories.RolesRepository;
import com.comm.SorveteriaResende.Domain.Models.Role;
import org.springframework.context.annotation.Configuration;


import javax.annotation.PostConstruct;

@Configuration
public class RoleSeeder {

    RolesRepository _rolesRepository;

    public RoleSeeder (RolesRepository rolesRepository) {
        _rolesRepository = rolesRepository;
    }

    @PostConstruct
    public void roles () {
        Role findRoleUser = _rolesRepository.findBynome("ROLE_USER").orElse(null);

        if (findRoleUser == null) {
            Role role = new Role(null, "ROLE_USER");

            Role saveRole = _rolesRepository.save(role);
        }

        Role findRoleAdmin = _rolesRepository.findBynome("ROLE_ADMIN").orElse(null);

        if (findRoleAdmin == null) {
            Role role = new Role(null, "ROLE_ADMIN");

            Role saveRole = _rolesRepository.save(role);
        }
    }

}
