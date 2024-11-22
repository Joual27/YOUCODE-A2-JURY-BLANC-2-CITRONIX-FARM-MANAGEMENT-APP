package org.youcode.CITRONIX.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youcode.CITRONIX.shared.BaseEntity;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Harvest extends BaseEntity {
    @Column
    private int year;
    @Column
    private String season;

    @Column
    private double quantity = 0;

    @ManyToOne
    @JoinColumn(name = "FIELD_ID")
    private Field field;

    @OneToMany(mappedBy = "harvest" , fetch = FetchType.EAGER)
    private List<TreeHarvest> treeHarvests;
}
