package id.ac.ui.cs.pustakaone.identity.repository;

import id.ac.ui.cs.pustakaone.identity.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("""
    select t from Token t inner join User u on t.user.id = u.id
    where u.id = :userId and (t.expired = false or t.revoked = false)
    """)
    List<Token> findAllValidTokensByUser(String userId);
    Optional<Token> findByTokenString(String tokenString);
}
