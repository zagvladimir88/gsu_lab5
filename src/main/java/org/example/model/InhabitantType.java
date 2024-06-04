package org.example.model;


import lombok.Data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "inhabitant_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InhabitantType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "language", nullable = false, length = 50)
    private String language;
}