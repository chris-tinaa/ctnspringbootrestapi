package com.juaracoding.cspringbootrestapi.model;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "ModelX")
public class TugasA {

    @Id
    @NotNull
    @Column(name = "IDModelX", columnDefinition = "VARCHAR(30)")
    private String IDModelX;

    @NotNull
    @Column(name = "Alamat", columnDefinition = "NVARCHAR(255)")
    @ColumnDefault(value = "'BELUM DIISI'")
    private String Alamat;

    @NotNull
    @Column(name = "CreatedBy")
    @ColumnDefault(value = "0")
    private Long CreatedBy;

    @NotNull
    @Column(name = "CreatedDate", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Timestamp CreatedDate;

    @Column(name = "IsActive")
    private Short IsActive;

    @Column(name = "JenisKelamin", columnDefinition = "CHAR(1)")
    @ColumnDefault(value = "'P'")
    private String JenisKelamin;

    @Column(name = "MasihHidup")
    private Boolean MasihHidup;

    @Column(name = "ModifiedBy")
    private Long ModifiedBy;

    @Column(name = "ModifiedDate", columnDefinition = "DATETIME")
    private Timestamp ModifiedDate;

    @NotNull
    @Column(name = "Nama", columnDefinition = "CHAR(40)")
    @ColumnDefault(value = "'SEDANG DIMINTA'")
    private String Nama;

    @Column(name = "TanggalLahir")
    private Date TanggalLahir;

}
