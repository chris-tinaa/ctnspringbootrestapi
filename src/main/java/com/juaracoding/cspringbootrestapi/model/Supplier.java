package com.juaracoding.cspringbootrestapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MstSupplier")
public class Supplier implements Serializable {

    private static final Long serializeVersion = 70003L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDSupplier")
    private Long idSupplier;

    @Column(name = "NamaSupplier")
    private String namaSupplier;

    @Column(name = "AlamatSupplier")
    private String alamatSupplier;

    @ManyToMany
    @JoinTable(name = "MapBarangSupplier",
            joinColumns = {@JoinColumn(name = "IDSupplier",referencedColumnName = "IDSupplier",foreignKey =@ForeignKey(name = "fkMapToSupplier"))},
            inverseJoinColumns = {@JoinColumn(name = "IDBarang",referencedColumnName = "IDBarang",foreignKey = @ForeignKey(name = "fkMapToBarang"))},
            uniqueConstraints = @UniqueConstraint(columnNames = {"IDSupplier","IDBarang"})
    )
    private List<Barang> listBarang;


    @Column(name = "CreatedBy",columnDefinition = "BIGINT NOT NULL default 1")
    private Long createdBy = 1L;

    @Column(name = "CreatedDate",columnDefinition = "DATETIME NOT NULL default GETDATE()")
    private Date createdDate=new Date();

    @Column(name = "ModifiedBy")
    private Long modifiedBy;

    @Column(name = "ModifiedDate",columnDefinition = "DATETIME NULL")
    private Date modifiedDate;

    @Column(name = "IsActive")
    private Byte isActive;

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

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

    public List<Barang> getListBarang() {
        return listBarang;
    }

    public void setListBarang(List<Barang> listBarang) {
        this.listBarang = listBarang;
    }
}