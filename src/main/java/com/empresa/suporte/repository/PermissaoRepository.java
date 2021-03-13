package com.empresa.suporte.repository;

import com.empresa.suporte.model.Permissao;
import com.empresa.suporte.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

    public List<Permissao> findByUsuariosIn(Usuario ... usuario);

}