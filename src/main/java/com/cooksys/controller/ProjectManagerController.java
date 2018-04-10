package com.cooksys.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

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

import com.cooksys.dto.ProjectManagerDto;
import com.cooksys.service.ProjectManagerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("projectManager")
public class ProjectManagerController {

	private ProjectManagerService projectManagerService;

	public ProjectManagerController(ProjectManagerService projectManagerService) {
		this.projectManagerService = projectManagerService;
	}

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllProjectManagers")
	public List<ProjectManagerDto> getAll(
			@RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dueDate) {
		if (dueDate == null) {
			return projectManagerService.getAll();
		} else {
			return projectManagerService.getOverdueProjectsByManager(dueDate);
		}
	}

	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	@ApiOperation(value = "", nickname = "projectManagerExistsForId")
	public void has(@PathVariable Long id, HttpServletResponse httpResponse) {
		if (!projectManagerService.has(id))
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}

	@GetMapping("{id}")
	@ApiOperation(value = "", nickname = "getProjectManagerById")
	public ProjectManagerDto get(@PathVariable Long id) {
		return projectManagerService.get(id);
	}

	@PostMapping
	@ApiOperation(value = "", nickname = "postNewProjectManager")
	public Long post(@RequestBody @Validated ProjectManagerDto projectManagerDto, HttpServletResponse httpResponse) {
		Long id = projectManagerService.post(projectManagerDto);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}

	@PutMapping("{id}")
	@ApiOperation(value = "", nickname = "putProjectManagerWithId")
	public void put(@PathVariable Long id, @RequestBody @Validated ProjectManagerDto projectManagerDto,
			HttpServletResponse httpResponse) {
		projectManagerService.put(id, projectManagerDto);
	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "", nickname = "deleteProjectManagerAtId")
	public void delete(@PathVariable Long id, HttpServletResponse httpResponse) {
		projectManagerService.delete(id);
	}

	
}
