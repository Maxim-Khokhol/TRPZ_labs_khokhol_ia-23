package com.proImg.image_editor.entities;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Collage> collages;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
