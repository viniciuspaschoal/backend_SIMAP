package com.simap.simap_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // <-- ajustado

    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    private String fotoUrl;

    private String role = "USER"; // Futuro admin, etc.

    public Users() {
    }

    public Users(String nome, String email, String fotoUrl) {
        this.nome = nome;
        this.email = email;
        this.fotoUrl = fotoUrl;
        this.role = "USER"; // padrão
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
