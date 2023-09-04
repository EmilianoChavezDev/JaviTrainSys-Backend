package com.py.javitrainsys.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "alumnos")
@Data
public class AlumnoEntity {
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
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "fecha_nacimiento")
    private Date fecha_nacimiento;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    private Boolean deleted = Boolean.FALSE;

}
