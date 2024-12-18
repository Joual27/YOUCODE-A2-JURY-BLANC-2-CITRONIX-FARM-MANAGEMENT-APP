package org.youcode.CITRONIX.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youcode.CITRONIX.shared.BaseEntity;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

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

    @OneToMany(mappedBy = "tree" , fetch = FetchType.EAGER)
    private List<TreeHarvest> treeHarvests;

    public boolean isProductive(){
        return Period.between(plantingDate , LocalDate.now()).getYears() < 20;
    }
}
