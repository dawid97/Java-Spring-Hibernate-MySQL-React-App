package com.javasolution.app.ppmtool.services;

import com.javasolution.app.ppmtool.domain.Backlog;
import com.javasolution.app.ppmtool.domain.ProjectTask;
import com.javasolution.app.ppmtool.repositories.BacklogRepository;
import com.javasolution.app.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask){

        //exceptions: Project not found
        //PTs to be added to a specific project, project != null, Backlog exists
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

        //set the Backlog to ProjectTask
        projectTask.setBacklog(backlog);

        //we want our project sequence to be like this: IDPRO-1 IDPRO-2 ...100 101
        Integer backlogSequence = backlog.getPTSequence();

        //update the Backlog sequence
        backlogSequence++;

        backlog.setPTSequence(backlogSequence);

        //add sequence to ProjectTask
        projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        //initial priority when priority is null
        if(projectTask.getPriority() == null){
            projectTask.setPriority(3);
        }

        //Initial status when status is null
        if(projectTask.getStatus()=="" || projectTask.getStatus() == null){
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findBacklogById(String id) {
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }
}
