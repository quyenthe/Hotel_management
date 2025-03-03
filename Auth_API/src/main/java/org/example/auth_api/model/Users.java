package org.example.auth_api.model;

import jakarta.persistence.*;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;


@Entity
@Table(name = "users") // Đổi tên bảng thành "users"
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    private String email;
    @ManyToOne
    @JoinColumn(name = "role", nullable = false) // Khóa ngoại tham chiếu đến Role
    private Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
