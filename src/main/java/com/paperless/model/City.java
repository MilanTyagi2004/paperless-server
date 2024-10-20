package com.paperless.model;

import com.paperless.model.baseModal.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="city")
public class City extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "city_id",unique = true)
    private long cityId;

    private String name;
    private boolean showInWebsite;


    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "state_id")
    private State state;


}
