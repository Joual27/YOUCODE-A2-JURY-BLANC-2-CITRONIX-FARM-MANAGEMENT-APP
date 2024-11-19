package org.youcode.CITRONIX.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youcode.CITRONIX.shared.BaseEntity;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tree extends BaseEntity {
    @Column(name = "PLANTING_DATE")
    private LocalDate plantingDate;

    @ManyToOne
    @JoinColumn(name = "FIELD_ID")
    private Field field;
}
