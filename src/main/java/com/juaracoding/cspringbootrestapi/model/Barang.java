package com.juaracoding.cspringbootrestapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "MstBarang")
public class Barang implements Serializable {
    private static final Long serializeVersion = 70002L;
    @Id
    @GeneratedValue
    @Column(name = "IDBarang")
    private Long idBarang;

    @Column(name = "NamaBarang")
    private String namaBarang;

    @Column(name = "Deskripsi", columnDefinition = "VARCHAR(50)")
    private String deskripsi;

    @ManyToOne
    @JoinColumn(name = "IDKategoriBarang", foreignKey = @ForeignKey(name = "fkBarangToKategori"))
    private KategoriBarang kategoriBarang;

    public Long getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(Long idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public KategoriBarang getKategoriBarang() {
        return kategoriBarang;
    }

    public void setKategoriBarang(KategoriBarang kategoriBarang) {
        this.kategoriBarang = kategoriBarang;
    }

    @ManyToMany(mappedBy = "listBarang")
    private List<Supplier> listSupplier;

    public List<Supplier> getListSupplier() {
        return listSupplier;
    }

    public void setListSupplier(List<Supplier> listSupplier) {
        this.listSupplier = listSupplier;
    }

}