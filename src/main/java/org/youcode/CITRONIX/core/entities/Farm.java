package org.youcode.CITRONIX.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youcode.CITRONIX.shared.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Farm extends BaseEntity {
    @Column
    private String name;

    @Column
    private String location ;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column
    private double surface;

    @OneToMany(mappedBy = "farm" , fetch = FetchType.EAGER)
    private List<Field> fields;
}
