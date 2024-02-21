package com.zja.entitys;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = 1954966334L;

    public static final QRole role = new QRole("role");

    public final com.zja.entitys.base.QBaseEntity _super = new com.zja.entitys.base.QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    //inherited
    public final StringPath id = _super.id;

    public final BooleanPath internal = createBoolean("internal");

    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = createDateTime("lastModifiedDate", java.time.LocalDateTime.class);

    public final SetPath<Org, QOrg> orgs = this.<Org, QOrg>createSet("orgs", Org.class, QOrg.class, PathInits.DIRECT2);

    public final StringPath remarks = createString("remarks");

    public final StringPath roleName = createString("roleName");

    public final NumberPath<Long> sort = createNumber("sort", Long.class);

    public final NumberPath<Integer> state = createNumber("state", Integer.class);

    public final SetPath<User, QUser> users = this.<User, QUser>createSet("users", User.class, QUser.class, PathInits.DIRECT2);

    public QRole(String variable) {
        super(Role.class, forVariable(variable));
    }

    public QRole(Path<? extends Role> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRole(PathMetadata metadata) {
        super(Role.class, metadata);
    }

}

