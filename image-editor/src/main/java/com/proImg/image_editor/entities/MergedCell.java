package com.proImg.image_editor.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "merged_cells")
public class MergedCell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int startRow;
    private int endRow;
    private int startCol;
    private int endCol;

    @ManyToOne
    @JoinColumn(name = "collage_id")
    private Collage collage;
}
