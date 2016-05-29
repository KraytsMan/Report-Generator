package com.softjourn.report.generator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Field {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String alias;

    @Column
    @ColumnDefault("'DESC'")
    private String sort;

    @Column(name = "show_able")
    private Boolean show = true;

    @Column(name = "show_order")
    private Integer order;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_report")
    private ReportSettings reportSettings;

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", sort='" + sort + '\'' +
                ", show=" + show +
                '}';
    }

}
