package com.ips.tpsi.pokemonapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "type_pokemon")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TypePokemon {

    @Id
    @Column(name = "id_type")
    private Integer idType;

    @Column(name = "name_type")
    private String nameType;

}
