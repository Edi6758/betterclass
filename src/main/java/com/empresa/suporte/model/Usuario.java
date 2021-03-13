package com.empresa.suporte.model;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Size(max = 200)
    private String nome;

    @NonNull
    @Column(unique = true)
    @Size(max = 100)
    private String cpf;

    @NonNull
    @Column(unique = true)
    @Size(max = 100)
    private String email;

    @NonNull
    @Column(unique = true)
    @Size(max = 100)
    private String login;

    @NonNull
    @Size(max = 100)
    private String senha;

    //------------IMAGEM------------

    @Column(nullable = true, length = 64)
    private String foto;


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Transient
    public String getFotoImagePath() {
        if (foto == null || id == null) return null;

        return "/usuario-foto/" + id + "/" + foto;
    }


    //-----------------------------
    @ManyToMany
    @JoinTable(
            name="usuario_permissao",
            joinColumns=@JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "permissao_id")
    )
    private List<Permissao> permissoes;

    //-----------------------------
    @ManyToMany
    @JoinTable(
            name="usuario_sala",
            joinColumns=@JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "sala_id")
    )
    private List<Sala> salas;
    //----------------------------

    //GET SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    @NonNull
    public String getCpf() {
        return cpf;
    }

    public void setCpf(@NonNull String cpf) {
        this.cpf = cpf;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getLogin() {
        return login;
    }

    public void setLogin(@NonNull String login) {
        this.login = login;
    }

    @NonNull
    public String getSenha() {
        return senha;
    }

    public void setSenha(@NonNull String senha) {
        this.senha = senha;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", foto='" + foto + '\'' +
                ", permissoes=" + permissoes +
                ", salas=" + salas +
                '}';
    }
}
