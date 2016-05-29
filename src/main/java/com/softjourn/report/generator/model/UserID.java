package com.softjourn.report.generator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Data
@Entity
public class UserID {

    @Id
    private Long id;

    @Column
    private String userID;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_report")
    private ReportSettings reportSettings;

    @Override
    public String toString() {
        return "UserID{" +
                "id=" + id +
                ", userID='" + userID + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserID userID = (UserID) o;
        return Objects.equals(id, userID.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
