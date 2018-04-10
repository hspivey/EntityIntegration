package com.cooksys.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;

import com.cooksys.dto.ProjectManagerDto;
import com.cooksys.entity.ProjectManager;

@Mapper(componentModel = "spring", uses = { ReferenceMapper.class })
public interface ProjectManagerMapper {

	ProjectManagerDto toDto(ProjectManager entity);

	ProjectManager toEntity(ProjectManagerDto dto);

	//ProjectManagerDto toDto(Optional<ProjectManager> findById);

}
