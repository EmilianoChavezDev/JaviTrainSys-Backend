package com.py.javitrainsys.Repository;

import com.py.javitrainsys.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

   public Optional<UsuarioEntity> findById(final Long id);

}


