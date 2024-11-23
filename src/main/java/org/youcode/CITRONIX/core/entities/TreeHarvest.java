package org.youcode.CITRONIX.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youcode.CITRONIX.core.embeddebales.TreeHarvestKey;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TreeHarvest {
     @EmbeddedId
     private TreeHarvestKey id;
     @Column
     private double quantity;
     @ManyToOne
     @MapsId("treeId")
     @JoinColumn(name = "TREE_ID")
     private Tree tree;

     @ManyToOne
     @MapsId("harvestId")
     @JoinColumn(name = "HARVEST_ID")
     private Harvest harvest;
}
