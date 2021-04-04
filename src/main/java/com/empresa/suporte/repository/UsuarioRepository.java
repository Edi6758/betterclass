package com.empresa.suporte.repository;

import com.empresa.suporte.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByLogin(String login);
    public Usuario findByCpf(String cpf);
    //public Usuario findByTelefone(String telefone);
    public Usuario findByEmail(String email);

    public Usuario findByLoginAndIdNot(String login, Long id);
    //public Usuario findByTelefoneAndIdNot(String telefone, Long id);
    public Usuario findByEmailAndIdNot(String email, Long id);
}
