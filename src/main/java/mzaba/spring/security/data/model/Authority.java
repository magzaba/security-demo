package mzaba.spring.security.data.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    private String authority;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @Override
    public String toString() {
        return "UserGrantedAuthority{" +
                "id = " + id +
                ", authority = " + authority + " }";
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
