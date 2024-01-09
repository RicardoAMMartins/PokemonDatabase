package com.ips.tpsi.pokemonapp.Repository;
import com.ips.tpsi.pokemonapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositório é apenas conhecido como repositório p/ o sprint por causa da anotação
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // acesso à base de dados para obter uma classe que representa o utilizador
    // -> User
    User findUserByUsername(String username);

}
