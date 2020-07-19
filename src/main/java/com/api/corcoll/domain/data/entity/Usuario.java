package com.api.corcoll.domain.data.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    @Getter
    @Setter
    Long id;

    @Getter
    @Setter
    private String nombre;

    @Getter
    @Setter
    private String apellido;

    @Column(unique = true, length = 20)
    @Size(min = 4, max = 20, message = "debe contener entre 4 y 20 caracteres")
    @Getter
    @Setter
    private String username;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    @Getter
    @Setter
    private Date fechaNacimiento;

    @Getter
    @Setter
    private Integer edad;

    @Getter
    @Setter
    private String sexo;

    @Getter
    @Setter
    private String email;

    @Size(min = 5, max = 20, message = "debe contener entre 5 y 20 caracteres")
    @Getter
    @Setter
    private String password;

    @Column(name = "foto_perfil")
    @Getter
    @Setter
    private String fotoPerfil;

    @Getter
    @Setter
    private Boolean enabled;

    @Getter
    @Setter
    private String descripcion;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"id_rol", "id_usuario"})})

    @Getter
    @Setter
    private List<Rol> roles;

    private static final long serialVersionUID = 1L;
}
