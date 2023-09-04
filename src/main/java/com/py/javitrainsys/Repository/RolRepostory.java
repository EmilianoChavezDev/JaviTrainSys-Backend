package com.py.javitrainsys.Repository;

import com.py.javitrainsys.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepostory extends JpaRepository<RolEntity, Long> {

    public Optional<RolEntity> findById(final Long id);
}
