package com.cooksys.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;

import com.cooksys.dto.ProjectDto;
import com.cooksys.entity.Project;

@Mapper(componentModel = "spring", uses = { ReferenceMapper.class })
public interface ProjectMapper {
	ProjectDto toDto(Project entity);

	Project toEntity(ProjectDto dto);

	//ProjectDto toDto(Optional<Project> findById);
	
}
