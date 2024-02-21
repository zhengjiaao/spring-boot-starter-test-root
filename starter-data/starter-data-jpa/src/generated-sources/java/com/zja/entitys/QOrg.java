package com.zja.entitys;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrg is a Querydsl query type for Org
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrg extends EntityPathBase<Org> {

    private static final long serialVersionUID = 201607964L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrg org = new QOrg("org");

    public final com.zja.entitys.base.QBaseTreeEntity _super;

    public final SetPath<Org, QOrg> children = this.<Org, QOrg>createSet("children", Org.class, QOrg.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    //inherited
    public final BooleanPath hasChildren;

    //inherited
    public final StringPath id;

    public final BooleanPath internal = createBoolean("internal");

    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = createDateTime("lastModifiedDate", java.time.LocalDateTime.class);

    //inherited
    public final StringPath levelIds;

    //inherited
    public final StringPath levelNames;

    public final StringPath name = createString("name");

    public final EnumPath<com.zja.define.OrgTypeEnum> orgType = createEnum("orgType", com.zja.define.OrgTypeEnum.class);

    public final QOrg parent;

    public final StringPath regionCode = createString("regionCode");

    public final SetPath<Role, QRole> roles = this.<Role, QRole>createSet("roles", Role.class, QRole.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> sort;

    public final NumberPath<Integer> state = createNumber("state", Integer.class);

    public final SetPath<User, QUser> users = this.<User, QUser>createSet("users", User.class, QUser.class, PathInits.DIRECT2);

    public QOrg(String variable) {
        this(Org.class, forVariable(variable), INITS);
    }

    public QOrg(Path<? extends Org> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrg(PathMetadata metadata, PathInits inits) {
        this(Org.class, metadata, inits);
    }

    public QOrg(Class<? extends Org> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.zja.entitys.base.QBaseTreeEntity(type, metadata, inits);
        this.hasChildren = _super.hasChildren;
        this.id = _super.id;
        this.levelIds = _super.levelIds;
        this.levelNames = _super.levelNames;
        this.parent = inits.isInitialized("parent") ? new QOrg(forProperty("parent")) : null;
        this.sort = _super.sort;
    }

}

