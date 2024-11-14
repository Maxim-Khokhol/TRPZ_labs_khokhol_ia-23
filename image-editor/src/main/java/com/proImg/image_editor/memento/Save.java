package com.proImg.image_editor.memento;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class Save {
    private final String version;
    private final Date date;
}
