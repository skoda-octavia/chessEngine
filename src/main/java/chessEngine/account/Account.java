package chessEngine.account;

import chessEngine.currentGame.CurrentGame;
import chessEngine.gameRecord.GameRecord;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Data
@Entity
@Table(name = "account")
@NoArgsConstructor
public class Account implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    private Long id;

    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;
    @Column(name = "email", length = 50, unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    private Boolean locked = false;
    private Boolean enabled = false;
    @Enumerated(EnumType.STRING)
    private AccountRole accountRole;

    @OneToOne(mappedBy = "account", optional = true)
    @PrimaryKeyJoinColumn(name = "current_game_id")
    private CurrentGame currentGame;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<GameRecord> gameRecordList = new ArrayList<>();


    public Account(String userName, String email, String password, AccountRole accountRole) {
        this.username = userName;
        this.email = email;
        this.password = password;
        this.accountRole = accountRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(accountRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
