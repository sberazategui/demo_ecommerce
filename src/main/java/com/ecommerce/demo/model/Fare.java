package com.ecommerce.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "fares")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date startDate;
    private Date endDate;
    private Long fareValue;
    private String currencyIso;
    private Integer priority;

    @OneToMany(mappedBy = "fare", fetch = FetchType.LAZY)
    private List<Price> prices;

    @Override
    public String toString() {
        return "Fare{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
