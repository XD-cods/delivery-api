package com.vlad.menuservice.util.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuMapper {
  private final ModelMapper modelMapper;
}
