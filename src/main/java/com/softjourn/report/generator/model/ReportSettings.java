package com.softjourn.report.generator.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class ReportSettings {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean allowFixedUserIds = false;

    @Column
    private Boolean allowFixedDocumentIds = false;

    @Column
    private Boolean enableUserMissedData = true;

    @Column
    private Boolean enableDocumentMissedData = true;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "reportSettings",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Field> fields;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "reportSettings",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<UserID> userIDs;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "reportSettings",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<DocumentID> documentIDs;

    @Override
    public String toString() {
        return "ReportSettings{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userMissedData=" + allowFixedUserIds +
                ", documentMissedData=" + allowFixedDocumentIds +
                ", enableUserMissedData=" + enableUserMissedData +
                ", enableDocumentMissedData=" + enableDocumentMissedData +
                ", fields=" + fields +
                ", userIDs=" + userIDs +
                ", documentIDs=" + documentIDs +
                '}';
    }
}
