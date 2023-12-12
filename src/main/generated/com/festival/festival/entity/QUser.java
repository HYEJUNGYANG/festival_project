package com.festival.festival.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1710532265L;

    public static final QUser user = new QUser("user");

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    public final StringPath e_list = createString("e_list");

    public final StringPath f_list = createString("f_list");

    public final ComparablePath<Character> gender = createComparable("gender", Character.class);

    public final StringPath id = createString("id");

    public final DateTimePath<java.time.LocalDateTime> join_date = createDateTime("join_date", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final StringPath nick = createString("nick");

    public final StringPath pw = createString("pw");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final StringPath tel = createString("tel");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

