package com.proImg.image_editor.service;

import com.proImg.image_editor.entities.Image;
import com.proImg.image_editor.entities.Project;
import com.proImg.image_editor.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    public Project createProjectWithName(String name) {
        Project project = new Project();
        project.setName(name);
        return projectRepository.save(project);
    }


    public List<Project> getAllProjects() {
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }


    public Project saveProjectWithDetails(Project project, MultipartFile[] files) throws IOException {
        List<Image> images = new ArrayList<>();

        for (MultipartFile file : files) {
            Image image = new Image(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes(),
                    project
            );
            images.add(image);
        }

        project.setImages(images);
        return projectRepository.save(project);
    }


    public Project updateProject(Long projectId, Project updatedProject, MultipartFile[] files) throws IOException {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setName(updatedProject.getName());
        project.getCollages().clear();
        project.getCollages().addAll(updatedProject.getCollages());

        if (files != null) {
            List<Image> images = new ArrayList<>();
            for (MultipartFile file : files) {
                Image image = new Image(
                        file.getOriginalFilename(),
                        file.getContentType(),
                        file.getBytes(),
                        project
                );
                images.add(image);
            }
            project.getImages().clear();
            project.getImages().addAll(images);
        }

        return projectRepository.save(project);
    }
}
