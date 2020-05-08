package com.techeasy.contas.receber.infra.repositories;

import com.techeasy.contas.receber.domain.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
