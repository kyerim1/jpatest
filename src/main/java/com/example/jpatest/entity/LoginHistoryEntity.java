package com.example.jpatest.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="login_history")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long testMemberEntityId;

    @Column
    private String ipAddr;

    @Column
    private LocalDateTime loginDate;
}
