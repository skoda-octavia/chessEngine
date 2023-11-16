package chessEngine.account;

import chessEngine.currentGame.CurrentGame;
import chessEngine.gameRecord.GameRecord;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@Table
@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
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

    @OneToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "current_game_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private CurrentGame currentGame;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<GameRecord> gameRecordList = new ArrayList<>();


    public Account(String userName, String email, String password, AccountRole accountRole) {
        this.username = userName;
        this.email = email;
        this.password = password;
        this.accountRole = accountRole;
    }

    public void addGameRecord(GameRecord gameRecord) {
        this.gameRecordList.add(gameRecord);
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
