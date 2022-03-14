package com.example.scheduler.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * Qitem is a Querydsl query type for item
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class Qitem extends EntityPathBase<item> {

    private static final long serialVersionUID = -1769062375L;

    public static final Qitem item = new Qitem("item");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public Qitem(String variable) {
        super(item.class, forVariable(variable));
    }

    public Qitem(Path<? extends item> path) {
        super(path.getType(), path.getMetadata());
    }

    public Qitem(PathMetadata metadata) {
        super(item.class, metadata);
    }

}

