package com.juaracoding.cspringbootrestapi.model;

import com.juaracoding.cspringbootrestapi.util.ConstantCalonPeserta;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Peserta")
public class Peserta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nama;
    private String batch;
    private String alamat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getBatch() {
        return batch;
    }
    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

}