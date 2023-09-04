package com.py.javitrainsys.controller;


import com.py.javitrainsys.entity.RolEntity;
import com.py.javitrainsys.services.mapper.RolServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles/")
public class RolController {

    @Autowired
    private RolServices rolServices;

    // Me trae todos los alumnos
    @GetMapping("/")
    public ResponseEntity<List<RolEntity>> findAll() {
        return new ResponseEntity<>(rolServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<RolEntity>> findAll(@PathVariable final Long id) {
        return new ResponseEntity<>(rolServices.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<RolEntity> save(@Validated @RequestBody RolEntity rol) {
        RolEntity alumnoReturn = rolServices.save(rol);
        return new ResponseEntity<>(alumnoReturn, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody RolEntity rol) {
        return new ResponseEntity<>(rolServices.update(rol), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>(rolServices.delete(id), HttpStatus.OK);
    }

}
