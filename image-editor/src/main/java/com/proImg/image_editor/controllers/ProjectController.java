package com.proImg.image_editor.controllers;

import com.proImg.image_editor.entities.Project;
import com.proImg.image_editor.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @PostMapping("/createProject")
    public ResponseEntity<Project> createProject(@RequestParam("name") String name) {
        Project newProject = projectService.createProjectWithName(name);
        return ResponseEntity.ok(newProject);
    }


    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/saveProject")
    public ResponseEntity<Project> saveProject(
            @RequestPart("project") Project project,
            @RequestPart("images") MultipartFile[] images) {
        try {
            Project savedProject = projectService.saveProjectWithDetails(project, images);
            return ResponseEntity.ok(savedProject);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/updateProject/{id}")
    public ResponseEntity<Project> updateProject(
            @PathVariable Long id,
            @RequestPart("project") Project updatedProject,
            @RequestPart("images") MultipartFile[] images) {
        try {
            Project project = projectService.updateProject(id, updatedProject, images);
            return ResponseEntity.ok(project);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}



