package com.py.javitrainsys.controller;

import com.py.javitrainsys.entity.AlumnoEntity;
import com.py.javitrainsys.services.mapper.AlumnoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos/")
public class AlumnoControllers {

    @Autowired
    private AlumnoServices alumnoServices;

    // Me trae todos los alumnos
    @GetMapping("/")
    public ResponseEntity<List<AlumnoEntity>> findAll() {
        return new ResponseEntity<>(alumnoServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AlumnoEntity>> findAll(@PathVariable final Long id) {
        return new ResponseEntity<>(alumnoServices.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<AlumnoEntity> save(@Validated @RequestBody AlumnoEntity alumno) {
        AlumnoEntity alumnoReturn = alumnoServices.save(alumno);
        return new ResponseEntity<>(alumnoReturn, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody AlumnoEntity alumno) {
        return new ResponseEntity<>(alumnoServices.update(alumno), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>(alumnoServices.delete(id), HttpStatus.OK);
    }
}
