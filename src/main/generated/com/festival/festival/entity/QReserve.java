package com.festival.festival.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReserve is a Querydsl query type for Reserve
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReserve extends EntityPathBase<Reserve> {

    private static final long serialVersionUID = -75956962L;

    public static final QReserve reserve = new QReserve("reserve");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Long> e_idx = createNumber("e_idx", Long.class);

    public final StringPath e_name = createString("e_name");

    public final NumberPath<Integer> e_price = createNumber("e_price", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> now_date = createDateTime("now_date", java.time.LocalDateTime.class);

    public final NumberPath<Integer> num = createNumber("num", Integer.class);

    public final StringPath pay = createString("pay");

    public final ComparablePath<Character> review = createComparable("review", Character.class);

    public final StringPath state = createString("state");

    public final NumberPath<Integer> total = createNumber("total", Integer.class);

    public final StringPath u_id = createString("u_id");

    public final StringPath u_name = createString("u_name");

    public final StringPath u_tel = createString("u_tel");

    public QReserve(String variable) {
        super(Reserve.class, forVariable(variable));
    }

    public QReserve(Path<? extends Reserve> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReserve(PathMetadata metadata) {
        super(Reserve.class, metadata);
    }

}

