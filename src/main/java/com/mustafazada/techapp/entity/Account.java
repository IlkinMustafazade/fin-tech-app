package com.mustafazada.techapp.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_accounts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "balance")
    BigDecimal balance;
    @Column(name = "currency")
    Currency currency;
    @Column(name = "account_status")
    Boolean isActive;
    @Column(name = "account_number", unique = true)
    Integer accountNo;
    @ManyToOne
    @JoinColumn(name = "user_id")
    TechUser user;
}
