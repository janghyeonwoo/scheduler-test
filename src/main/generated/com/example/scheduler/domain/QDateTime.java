package com.example.scheduler.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDateTime is a Querydsl query type for DateTime
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QDateTime extends EntityPathBase<DateTime> {

    private static final long serialVersionUID = 1333552577L;

    public static final QDateTime dateTime = new QDateTime("dateTime");

    public final DateTimePath<java.time.LocalDateTime> endDateTime = createDateTime("endDateTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> startDateTime = createDateTime("startDateTime", java.time.LocalDateTime.class);

    public QDateTime(String variable) {
        super(DateTime.class, forVariable(variable));
    }

    public QDateTime(Path<? extends DateTime> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDateTime(PathMetadata metadata) {
        super(DateTime.class, metadata);
    }

}

