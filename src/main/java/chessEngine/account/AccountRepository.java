package chessEngine.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional()
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Override
    Optional<Account> findById(Long aLong);
    Optional<Account> findByEmail(String email);
    Optional<Account> findByUsername(String username);

    @Override
    List<Account> findAll();

    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.enabled = TRUE WHERE a.email = :email")
    int enableAccount(@Param("email") String email);


    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.currentGame.id = :id WHERE a.email = :email")
    int updateCurrentGame(@Param("email") String email, @Param("id") Long id);

}
