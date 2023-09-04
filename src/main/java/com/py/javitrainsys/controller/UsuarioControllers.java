package com.py.javitrainsys.controller;

import com.py.javitrainsys.entity.UsuarioEntity;
import com.py.javitrainsys.services.mapper.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/usuarios/")
public class UsuarioControllers {

    @Autowired
    private UsuarioServices usuarioServices;


    @GetMapping("/")
    public ResponseEntity<List<Map<String, Object>>> findAll() {
        List<Map<String, Object>> usuariosPersonalizados = usuarioServices.listarUsuariosConRolesPersonalizados();
        return ResponseEntity.ok(usuariosPersonalizados);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody UsuarioEntity nuevoUsuario) {
        // Asegúrate de que el JSON enviado no tenga un ID asignado
        if (nuevoUsuario.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede especificar un ID al crear un nuevo usuario.");
        }

        nuevoUsuario = usuarioServices.save(nuevoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con ID: " + nuevoUsuario.getId());
    }


    @PostMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody UsuarioEntity usuario) {
        return new ResponseEntity<>(usuarioServices.update(usuario), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioServices.delete(id), HttpStatus.OK);
    }
}