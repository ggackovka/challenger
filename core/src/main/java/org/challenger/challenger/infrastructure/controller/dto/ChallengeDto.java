package org.challenger.challenger.infrastructure.controller.dto;

import lombok.Builder;


import java.util.List;

@Builder
public record ChallengeDto(
	String id,
	String name,
	String type,
	String state,
	Integer goal,
	Integer progress,
	UserDto owner,
	List<UserDto> users,
	List<SubmissionDto> submissions
) {
	public record UserDto(String userId) {}
	public record SubmissionDto(String id, String userId, Integer value) {}
}

