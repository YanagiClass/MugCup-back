package com.example.mugcupserver.entity;


import com.example.mugcupserver.common.BaseTimeEntity;
import com.example.mugcupserver.common.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Member extends BaseTimeEntity implements UserDetails {


    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    private Role roles;


    //==생성자==
    @Builder
    public Member(String email, String password, String username, Role roles) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.roles = roles;
    }

    //==update
    public void update(String password, String username){
        this.password = password;
        this.username = username;
    }



    //UserDetails implements --> Spring Security에서 사용자의 Auth정보를 저장할 때 사용되는 인터페이스이다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
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
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
