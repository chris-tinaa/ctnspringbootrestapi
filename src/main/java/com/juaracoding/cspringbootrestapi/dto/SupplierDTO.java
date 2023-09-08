package com.juaracoding.cspringbootrestapi.dto;

import java.util.List;

public class SupplierDTO {
    private Long idSupplier;
    private String namaSupplier;

    private String alamatSupplier;
    private List<BarangDTO> listBarang;

    public Long getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(Long idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
    }

    public String getAlamatSupplier() {
        return alamatSupplier;
    }

    public void setAlamatSupplier(String alamatSupplier) {
        this.alamatSupplier = alamatSupplier;
    }

    public List<BarangDTO> getListBarang() {
        return listBarang;
    }

    public void setListBarang(List<BarangDTO> listBarang) {
        this.listBarang = listBarang;
    }
}
