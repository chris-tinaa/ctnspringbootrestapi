package com.juaracoding.cspringbootrestapi.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "ModelA")
public class ModelA {

    @Id
    @NotNull
    @Column(name = "IDModelA")
    private Long IDModelA;

    @NotNull
    @Column(name = "CreatedBy")
    @ColumnDefault(value = "1")
    private Long CreatedBy;

    @NotNull
    @Column(name = "CreatedDate", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Timestamp CreatedDate;

    @Column(name = "IsActive")
    private Short IsActive;

    @NotNull
    @Column(name = "ModelA", columnDefinition = "CHAR(20)")
    @ColumnDefault(value = "'Default model A'")
    private String ModelA;

    @Column(name = "ModifiedBy")
    private Long ModifiedBy;

    @Column(name = "ModifiedDate", columnDefinition = "DATETIME")
    private Timestamp ModifiedDate;

    @OneToMany(mappedBy = "modelA")
    private List<ModelB> listModelB;
}
