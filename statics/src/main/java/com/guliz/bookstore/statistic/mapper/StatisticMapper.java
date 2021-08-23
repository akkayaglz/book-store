package com.guliz.bookstore.statistic.mapper;

import com.guliz.bookstore.statistic.model.StatisticRequest;
import com.guliz.bookstore.statistic.model.StatisticResponse;
import com.guliz.bookstore.statistic.model.StatisticDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatisticMapper {

    StatisticMapper INSTANCE = Mappers.getMapper(StatisticMapper.class);

    StatisticResponse toStatisticResponse(StatisticDto statisticDto);

    StatisticDto toStatisticDto(StatisticRequest statisticRequest);


}
