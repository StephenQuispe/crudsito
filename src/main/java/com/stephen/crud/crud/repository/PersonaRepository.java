package com.stephen.crud.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stephen.crud.crud.models.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    
}
