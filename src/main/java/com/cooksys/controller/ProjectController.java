package com.cooksys.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.dto.ProjectDto;
import com.cooksys.entity.Project;
import com.cooksys.entity.ProjectManager;
import com.cooksys.service.ProjectService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("project")
public class ProjectController {

	private ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllProjectManagers")
	public List<ProjectDto> getAll(@RequestParam(value="date", required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
		if (date == null) {
		return projectService.getAll();
	} else {
		return projectService.getOverdueProjects(date);
	}
	}
	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	@ApiOperation(value = "", nickname = "projectExistsForId")
	public void has(@PathVariable Long id, HttpServletResponse httpResponse) {
		if (!projectService.has(id))
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}

	@GetMapping("{id}")
	@ApiOperation(value = "", nickname = "getProjectById")
	public ProjectDto get(@PathVariable Long id) {
		return projectService.get(id);
	}

	@GetMapping("/projectManager/{id}")
		@ApiOperation(value = "", nickname = "getProjectsByManager")
	public List<ProjectDto> getByManager(@PathVariable Long id){
		return projectService.getByManager(id);
	}
	
	@PostMapping
	@ApiOperation(value = "", nickname = "postNewProject")
	public Long post(@RequestBody @Validated ProjectDto projectDto, HttpServletResponse httpResponse) {
		Long id = projectService.post(projectDto);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}

	@PutMapping("{id}")
	@ApiOperation(value = "", nickname = "putProjectWithId")
	public void put(@PathVariable Long id, @RequestBody @Validated ProjectDto projectDto, HttpServletResponse httpResponse) {
		projectService.put(id, projectDto);
	}
	
	@DeleteMapping("{id}")
	@ApiOperation(value = "", nickname = "deleteProjectAtId")
	public void delete(@PathVariable Long id, HttpServletResponse httpResponse) {
		projectService.delete(id);
	}
	
	@PostMapping("{id}/manager/{managerId}")
	@Transactional
	public void attachManager(@PathVariable(name = "id") Project project, @PathVariable(name = "managerId") ProjectManager pm) {
		project.setManager(pm);
		pm.getProjects().add(project);
	}

}