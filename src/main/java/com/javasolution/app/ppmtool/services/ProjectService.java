package com.javasolution.app.ppmtool.services;

import com.javasolution.app.ppmtool.domain.Project;
import com.javasolution.app.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        //logic

        return projectRepository.save(project);
    }
}
