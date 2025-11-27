package com.poc.kafka.dto.serdes;


import com.poc.kafka.dto.User;
import com.poc.kafka.dto.User2;
import com.poc.kafka.dto.User3;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomMapper {

    @Mapping(target = "ideUser", source = "id")
    User2 toUser2(User user);

    @Mapping(target = "id", source = "ideUser")
    User toUser1(User2 user2);

    @Mapping(target = "idUser", source = "id")
    User3 toUser3(User user);

}
