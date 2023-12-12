package com.festival.festival.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExp is a Querydsl query type for Exp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExp extends EntityPathBase<Exp> {

    private static final long serialVersionUID = 609352575L;

    public static final QExp exp = new QExp("exp");

    public final StringPath content = createString("content");

    public final NumberPath<Long> count = createNumber("count", Long.class);

    public final StringPath detail = createString("detail");

    public final StringPath filename = createString("filename");

    public final StringPath filepath = createString("filepath");

    public final NumberPath<Double> hardness = createNumber("hardness", Double.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath l_name = createString("l_name");

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final StringPath link = createString("link");

    public final StringPath name = createString("name");

    public final StringPath place = createString("place");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath tag = createString("tag");

    public final StringPath tel = createString("tel");

    public final StringPath time = createString("time");

    public final StringPath warning = createString("warning");

    public final StringPath zone = createString("zone");

    public QExp(String variable) {
        super(Exp.class, forVariable(variable));
    }

    public QExp(Path<? extends Exp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExp(PathMetadata metadata) {
        super(Exp.class, metadata);
    }

}

