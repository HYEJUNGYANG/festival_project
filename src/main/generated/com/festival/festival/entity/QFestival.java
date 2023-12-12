package com.festival.festival.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFestival is a Querydsl query type for Festival
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFestival extends EntityPathBase<Festival> {

    private static final long serialVersionUID = -1779968682L;

    public static final QFestival festival = new QFestival("festival");

    public final StringPath detail = createString("detail");

    public final DatePath<java.time.LocalDate> end = createDate("end", java.time.LocalDate.class);

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

    public final DatePath<java.time.LocalDate> start = createDate("start", java.time.LocalDate.class);

    public final StringPath tag = createString("tag");

    public final StringPath tel = createString("tel");

    public final StringPath time = createString("time");

    public final StringPath zone = createString("zone");

    public QFestival(String variable) {
        super(Festival.class, forVariable(variable));
    }

    public QFestival(Path<? extends Festival> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFestival(PathMetadata metadata) {
        super(Festival.class, metadata);
    }

}

