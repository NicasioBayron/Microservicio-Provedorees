package com.proveedores.provedorees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proveedores.provedorees.model.Provedorees;

@Repository
public interface ProvedoreesRepository extends JpaRepository<Provedorees, Long> {

}
