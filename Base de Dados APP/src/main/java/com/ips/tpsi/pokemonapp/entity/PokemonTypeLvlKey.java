        package com.ips.tpsi.pokemonapp.entity;

        import java.io.Serializable;

        import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        import lombok.AllArgsConstructor;
        import lombok.EqualsAndHashCode;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;


        @Embeddable
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        @EqualsAndHashCode
        public class PokemonTypeLvlKey implements Serializable {

            @Column(name = "pokemon_id")
            private Integer pokemonId;

            @Column(name = "type_id")
            private Integer typeId;
        }
