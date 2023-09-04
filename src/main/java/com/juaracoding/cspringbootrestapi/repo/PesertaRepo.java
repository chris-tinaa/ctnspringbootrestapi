package com.juaracoding.cspringbootrestapi.repo;

import com.juaracoding.cspringbootrestapi.model.Peserta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PesertaRepo extends JpaRepository<Peserta, Integer> {
    public List<Peserta> findByNama(String n);

    public List<Peserta> findByNamaContainsAndBatch(String n, String b);
    public List<Peserta> findByNamaContains(String n);

    public List<Peserta> findByNamaStartsWith(String n);
    public List<Peserta> findByNamaEndsWith(String n);

}
