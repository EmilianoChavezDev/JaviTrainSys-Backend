package com.py.javitrainsys.Repository;

import com.py.javitrainsys.entity.AlumnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<AlumnoEntity, Long>{

public Optional<AlumnoEntity> findById(final Long id);

    @Query(value = "SELECT * FROM  ALUMNOS  WHERE ALUMNOS.DELETED = :estado  ", nativeQuery = true)
    public List<AlumnoEntity> findAllAlumnosEntityAndDeleted(final boolean estado);


}
