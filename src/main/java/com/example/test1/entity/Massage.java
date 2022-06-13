package com.example.test1.entity;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Builder
@Entity

@Table(name = "massages", schema = "public")
public class Massage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "text")
    private String text;
    @Column(name = "tag")
    private String tag;
    @Column(name = "data_sent")
    private LocalDateTime data_sent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User autor;

    public User getAutor() {
        return autor;
    }

    public String getAuthorName() {
        return autor != null ? autor.getUsername() : "none";
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }


}
