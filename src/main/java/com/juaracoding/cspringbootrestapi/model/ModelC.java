package com.juaracoding.cspringbootrestapi.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "ModelC")
public class ModelC {
    @Id
    @NotNull
    @Column(name = "IDModelC")
    private Long IDModelC;

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
    @Column(name = "ModelC", columnDefinition = "CHAR(25)")
    @ColumnDefault(value = "'Default model C'")
    private String ModelC;

    @Column(name = "ModifiedBy")
    private Long ModifiedBy;

    @Column(name = "ModifiedDate", columnDefinition = "DATETIME")
    private Timestamp ModifiedDate;

    @ManyToMany
    @JoinTable(name = "MapModelBModelC",
            joinColumns = {@JoinColumn(name = "IDModelC",referencedColumnName = "IDModelC",foreignKey =@ForeignKey(name = "fkMapToModelC"))},
            inverseJoinColumns = {@JoinColumn(name = "IDModelB",referencedColumnName = "IDModelB",foreignKey = @ForeignKey(name = "fkMapToModelB"))},
            uniqueConstraints = @UniqueConstraint(columnNames = {"IDModelC","IDModelB"})
    )
    private List<ModelB> listModelB;
}
