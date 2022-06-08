package io.codoctet.ikea.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Furniture {

    @Id
    private Long id;

    private String name;

    private float price;

    private float height;

    private float width;

    private float depth;

    private float weight;

}
