package com.example.scheduler.repository;

import com.example.scheduler.domain.QTeam;
import com.example.scheduler.domain.QUser;
import com.example.scheduler.domain.Team;
import com.example.scheduler.dto.QUserSearchDto;
import com.example.scheduler.dto.UserSearchDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository{
    private final JPAQueryFactory jpaQueryFactory;
    QUser user = QUser.user;
    QTeam team = QTeam.team;

    @Override
    public QueryResults<UserSearchDto> findSearch(UserSearchDto searchDto){
        return null;
//        return jpaQueryFactory
//                .select(new QUserSearchDto(
//                        user.name,
//                        user.age,
//                        user
//
//                ))
//                .from(user)
//                .innerJoin(team)
//                .on(user.team.id.eq(team.id))
//                .where(searchWhere(searchDto))
//                .fetchResults();
    };

    private BooleanBuilder searchWhere(UserSearchDto searchDto){
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(geoAge(searchDto.getAge()));
        builder.and(likeName(searchDto.getName()));
    return builder;
    }

    private BooleanExpression geoAge(Integer age){
        if(age == null || age == 0){
            return null;
        }
        return user.age.goe(age);
    }

    private BooleanExpression likeName(String name){
        if(StringUtils.isEmpty(name)){
            return null;
        }
        return user.name.like(name);
    }

}
