package com.thiagobsn.api.domain.medico.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thiagobsn.api.domain.medico.entity.Medico;
import com.thiagobsn.api.domain.medico.util.Especialidade;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    @Query("""
            SELECT m From Medico m
            WHERE m.ativo = 1
            AND m.especialidade = :especialidade
            AND m.id NOT IN ( SELECT c.medico.id FROM Consulta c WHERE c.data = :data)
            ORDER BY rand()
            limit 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            select m.ativo
            from Medico m
            where
            m.id = :id
            """)
    Boolean findAtivoById(Long id);

}
