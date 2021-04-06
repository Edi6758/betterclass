package com.empresa.suporte.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity(name = "sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Basic
    private String titulo;

    @NonNull
    @Basic
    private int limite;

    @Basic
    private java.time.LocalTime hora;

    @NonNull
    @Basic
    private String semana;

    @NonNull
    @Basic
    private String local;

    @NonNull
    @Basic
    private String descricao;


    @ManyToMany(mappedBy = "salas")
    private List<Usuario> usuarios;

    //GET SET


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @NonNull
    public String getSemana() {
        return semana;
    }

    public void setSemana(@NonNull String semana) {
        this.semana = semana;
    }

    @NonNull
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NonNull String titulo) {
        this.titulo = titulo;
    }

    @NonNull
    public String getLocal() {
        return local;
    }

    public void setLocal(@NonNull String local) {
        this.local = local;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @NonNull
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NonNull String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", limite=" + limite +
                ", hora=" + hora +
                ", semana='" + semana + '\'' +
                ", local='" + local + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
