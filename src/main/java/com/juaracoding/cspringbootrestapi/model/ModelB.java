package com.juaracoding.cspringbootrestapi.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "ModelB")
public class ModelB {

    @Id
    @NotNull
    @Column(name = "IDModelB")
    private Long IDModelB;

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
    @Column(name = "ModelB", columnDefinition = "CHAR(30)")
    @ColumnDefault(value = "'Default model B'")
    private String ModelB;

    @Column(name = "ModifiedBy")
    private Long ModifiedBy;

    @Column(name = "ModifiedDate", columnDefinition = "DATETIME")
    private Timestamp ModifiedDate;

    @ManyToOne
    @JoinColumn(name = "IDModelA", foreignKey = @ForeignKey(name = "fkModelBToModelA", foreignKeyDefinition = "FOREIGN KEY ([IDModelA]) REFERENCES [ModelA] ([IDModelA]) ON DELETE SET NULL ON UPDATE SET NULL"))
    private ModelA modelA;

    @ManyToMany(mappedBy = "listModelB")
    private List<ModelC> listModelC;

}
