package com.festival.festival.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -1249283402L;

    public static final QReview review = new QReview("review");

    public final StringPath content = createString("content");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Long> e_idx = createNumber("e_idx", Long.class);

    public final StringPath e_name = createString("e_name");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final NumberPath<Integer> star = createNumber("star", Integer.class);

    public final StringPath u_id = createString("u_id");

    public final StringPath u_nick = createString("u_nick");

    public QReview(String variable) {
        super(Review.class, forVariable(variable));
    }

    public QReview(Path<? extends Review> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReview(PathMetadata metadata) {
        super(Review.class, metadata);
    }

}

