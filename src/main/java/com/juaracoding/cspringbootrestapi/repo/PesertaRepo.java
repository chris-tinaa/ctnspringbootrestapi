package com.juaracoding.cspringbootrestapi.repo;

import com.juaracoding.cspringbootrestapi.model.Peserta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PesertaRepo extends JpaRepository<Peserta, Integer> {
}
