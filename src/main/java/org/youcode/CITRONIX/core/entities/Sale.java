package org.youcode.CITRONIX.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youcode.CITRONIX.shared.BaseEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sale extends BaseEntity {

    @Column
    private double quantity;
    @Column(name = "UNIT_PRICE")
    private double unitPrice;

    @Column(name = "CLIENT_NAME")
    private String clientName;

    @ManyToOne
    @JoinColumn(name = "HARVEST_ID")
    private Harvest harvest;
}
