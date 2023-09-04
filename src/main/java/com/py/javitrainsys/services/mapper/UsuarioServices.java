package com.py.javitrainsys.services.mapper;

import com.py.javitrainsys.Repository.RolRepostory;
import com.py.javitrainsys.Repository.UsuarioRepository;
import com.py.javitrainsys.entity.RolEntity;
import com.py.javitrainsys.entity.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioServices {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepostory rolRepository;


    @Transactional
    public List<UsuarioEntity> findAll() {
        List<UsuarioEntity> usuarioEntities = usuarioRepository.findAll();
        return usuarioEntities;
    }

    @Transactional
    public List<Map<String, Object>> listarUsuariosConRolesPersonalizados() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        List<Map<String, Object>> usuariosPersonalizados = new ArrayList<>();

        for (UsuarioEntity usuario : usuarios) {
            Map<String, Object> usuarioPersonalizado = new HashMap<>();
            usuarioPersonalizado.put("id", usuario.getId());
            usuarioPersonalizado.put("username", usuario.getUsername());
            usuarioPersonalizado.put("email", usuario.getEmail());
            usuarioPersonalizado.put("password", usuario.getPassword());
            usuarioPersonalizado.put("deleted", usuario.getDeleted());

            List<Map<String, Object>> rolesPersonalizados = new ArrayList<>();
            for (RolEntity rol : usuario.getRoles()) {
                Map<String, Object> rolPersonalizado = new HashMap<>();
                rolPersonalizado.put("id", rol.getId());
                rolPersonalizado.put("nombre_rol", rol.getNombre_rol());
                rolesPersonalizados.add(rolPersonalizado);
            }
            usuarioPersonalizado.put("roles", rolesPersonalizados);

            usuariosPersonalizados.add(usuarioPersonalizado);
        }

        return usuariosPersonalizados;
    }

    @Transactional
    public UsuarioEntity save(UsuarioEntity nuevoUsuario) {
        List<RolEntity> roles = new ArrayList<>();
        for (RolEntity rol : nuevoUsuario.getRoles()) {
            RolEntity rolExistente = rolRepository.findById(rol.getId())
                    .orElseThrow(null);
            roles.add(rolExistente);
        }

        nuevoUsuario.setRoles(roles);

        return usuarioRepository.save(nuevoUsuario);
    }


    @Transactional
    public Boolean update(UsuarioEntity usuario) {
        if (usuarioRepository.findById(usuario.getId()) != null) {
            usuarioRepository.save(usuario);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Transactional
    public String delete(Long id) {
        UsuarioEntity usuarioEliminar = usuarioRepository.findById(id).orElse(null);
        usuarioEliminar.setDeleted(Boolean.TRUE);
        usuarioRepository.save(usuarioEliminar);
        return "Eliminado con exito";
    }

}
