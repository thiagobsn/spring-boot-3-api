package com.thiagobsn.api.domain.paciente.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thiagobsn.api.domain.paciente.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    public Page<Paciente> findAllByAtivoTrue(Pageable pageable);

    @Query("""
            select p.ativo
            from Paciente p
            where
            p.id = :id
            """)
    Boolean findAtivoById(Long id);
    
}
