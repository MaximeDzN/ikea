package io.codoctet.ikea.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote {
    @Id
    private Long id;

    private Date date;

    private float sum;

    @OneToMany
    private Set<Furniture> furnitures;
}
