package org.youcode.CITRONIX.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youcode.CITRONIX.shared.BaseEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Field extends BaseEntity {
    @Column
    private double surface;
    @ManyToOne
    @JoinColumn(name = "FARM_ID")
    private Farm farm;
    @OneToMany(mappedBy = "field" , fetch = FetchType.EAGER)
    private List<Tree> trees;
}
