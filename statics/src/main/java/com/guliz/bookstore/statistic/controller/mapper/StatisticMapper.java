package com.guliz.bookstore.statistic.controller.mapper;

import com.guliz.bookstore.statistic.controller.model.StatisticRequest;
import com.guliz.bookstore.statistic.controller.model.StatisticResponse;
import com.guliz.bookstore.statistic.controller.service.model.StatisticDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatisticMapper {

    StatisticMapper INSTANCE = Mappers.getMapper(StatisticMapper.class);

    StatisticResponse toStatisticResponse(StatisticDto statisticDto);

    StatisticDto toStatisticDto(StatisticRequest statisticRequest);


}
