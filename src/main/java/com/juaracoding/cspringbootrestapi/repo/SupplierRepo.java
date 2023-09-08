package com.juaracoding.cspringbootrestapi.repo;

import com.juaracoding.cspringbootrestapi.model.Barang;
import com.juaracoding.cspringbootrestapi.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepo extends JpaRepository<Supplier,Long> {
    public Page<Supplier> findByNamaSupplierContains(Pageable p, String val);

}
