package io.github.cepr0.demo;

import lombok.Value;

import java.util.List;

@Value
public class ParentDto {
	private String name;
	private List<String> childNames;
}
