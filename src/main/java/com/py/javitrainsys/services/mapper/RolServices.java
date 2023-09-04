package com.py.javitrainsys.services.mapper;

import com.py.javitrainsys.Repository.RolRepostory;

import com.py.javitrainsys.entity.RolEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServices {
    @Autowired
    RolRepostory rolRepostory;


    @Transactional
    public List<RolEntity> findAll() {
        List<RolEntity> usuarioEntities = rolRepostory.findAll();
        return usuarioEntities;
    }

    @Transactional
    public Optional<RolEntity> findById(Long id) {
        Optional<RolEntity> alumnoEntities = rolRepostory.findById(id);
        return alumnoEntities;
    }


    @Transactional
    public RolEntity save(RolEntity rol) {
        return rolRepostory.save(rol);
    }


    @Transactional
    public Boolean update(RolEntity rol) {
        if (rolRepostory.findById(rol.getId()) != null) {
            rolRepostory.save(rol);
            return Boolean.TRUE;
        }
        return false;
    }

    @Transactional
    public String delete(Long id) {
        RolEntity rolEliminar = rolRepostory.findById(id).orElse(null);
        rolEliminar.setDeleted(Boolean.TRUE);
        rolRepostory.save(rolEliminar);
        return "Eliminado con exito";
    }
}
