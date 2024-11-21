package org.youcode.CITRONIX.core.embeddebales;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class TreeHarvestKey implements Serializable {
    @Column(name = "HARVEST_ID")
    private Long harvestId;
    @Column(name = "TREE_ID")
    private Long treeId;
}
