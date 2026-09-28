package com.enigma.lcloanmanajementsystem.entity;

import com.enigma.lcloanmanajementsystem.utils.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public UserEntity(String name, String email, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

//    name
    private String name;

//    email
    @Column(unique = true, nullable = false)
    private String email;

//    phoneNumber
    @Column(name = "phone_number")
    private String phoneNumber;
//    password
    @Column(nullable = false)
    private String password;

//    role
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<LoanEntity> loans;


}
