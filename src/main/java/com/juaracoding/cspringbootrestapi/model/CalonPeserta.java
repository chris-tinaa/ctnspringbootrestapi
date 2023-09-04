package com.juaracoding.cspringbootrestapi.model;

import com.juaracoding.cspringbootrestapi.util.ConstantCalonPeserta;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CalonPeserta {

    @NotNull(message = ConstantCalonPeserta.NAMA_NOT_NULL)
    @NotEmpty(message = ConstantCalonPeserta.NAMA_NOT_EMPTY)
    @Length(min = 5, max = 15,message = ConstantCalonPeserta.NAMA_MIN_MAX)
//    @Email(message = ConstantCalonPeserta.EMAIL_INVALID)
    @NotBlank(message = ConstantCalonPeserta.NAMA_NOT_BLANK)
    private String nama;
    @NotNull(message = ConstantCalonPeserta.UMUR_IS_MANDATORY)
    private Integer umur;
    @NotNull(message = ConstantCalonPeserta.NILAI_IS_MANDATORY)
    private Integer nilai;
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getUmur() {
        return umur;
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
    }

    public Integer getNilai() {
        return nilai;
    }

    public void setNilai(Integer nilai) {
        this.nilai = nilai;
    }
}
