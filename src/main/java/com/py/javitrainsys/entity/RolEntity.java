package com.py.javitrainsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @CreationTimestamp
    @Column(name = "fecha_creado", updatable = false)
    private LocalDateTime creado;
    @UpdateTimestamp
    @Column(name = "fecha_actualizado")
    private LocalDateTime actualizado;
    @Column(name = "nombre_rol")
    private String nombre_rol;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "roles")
    @JsonIgnore
    private List<UsuarioEntity> usuarios = new ArrayList<>();


    private Boolean deleted = Boolean.FALSE;


}
