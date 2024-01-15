package com.ips.tpsi.pokemonapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

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

    @OneToMany(mappedBy = "typePokemon")
    private List<PokemonTypeLvl> typeLevels;


}
