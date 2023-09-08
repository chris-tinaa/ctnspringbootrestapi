package com.juaracoding.cspringbootrestapi.repo;

import com.juaracoding.cspringbootrestapi.model.Barang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarangRepo  extends JpaRepository<Barang,Long> {

    public Page<Barang> findByNamaBarangContains(Pageable p, String val);
    public Page<Barang> findByDeskripsiContains(Pageable p, String val);


}
