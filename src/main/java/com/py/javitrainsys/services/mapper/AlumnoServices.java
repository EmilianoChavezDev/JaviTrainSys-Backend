package com.py.javitrainsys.services.mapper;

import com.py.javitrainsys.Repository.AlumnoRepository;
import com.py.javitrainsys.Repository.UsuarioRepository;
import com.py.javitrainsys.entity.AlumnoEntity;
import com.py.javitrainsys.entity.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServices {

    //Tengo instanciado todos los metodos de mi repositorio
    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public List<AlumnoEntity> findAll() {
        List<AlumnoEntity> alumnoEntities = alumnoRepository.findAll();
        return alumnoEntities;
    }

    @Transactional
    public Optional<AlumnoEntity> findById(Long id) {
        Optional<AlumnoEntity> alumnoEntities = alumnoRepository.findById(id);
        return alumnoEntities;
    }

    @Transactional
    public AlumnoEntity save(AlumnoEntity alumno) {
        Long usuarioId = alumno.getUsuario().getId(); // Obtengo el usuario_id desde la entidad AlumnoEntity

        UsuarioEntity usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null) {
            // Maneja el caso en el que el usuario no se encuentre en la base de datos
            System.out.println("no se encontro usuario");
        }
        // Asigna el usuario al alumno
        alumno.setUsuario(usuario);

        return alumnoRepository.save(alumno);

    }


    @Transactional
    public Boolean update(AlumnoEntity alumno) {
        if (alumnoRepository.findById(alumno.getId()) != null) {
            alumnoRepository.save(alumno);
            return Boolean.TRUE;
        }
        return false;
    }

    @Transactional
    public String delete(Long id) {
        AlumnoEntity alumnoEliminar = alumnoRepository.findById(id).orElse(null);
        alumnoEliminar.setDeleted(Boolean.TRUE);
        alumnoRepository.save(alumnoEliminar);
        return "Eliminado con exito";
    }
}
