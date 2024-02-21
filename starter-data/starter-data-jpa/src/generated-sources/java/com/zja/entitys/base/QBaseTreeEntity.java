package com.zja.entitys.base;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBaseTreeEntity is a Querydsl query type for BaseTreeEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseTreeEntity extends EntityPathBase<BaseTreeEntity<?>> {

    private static final long serialVersionUID = -1885041657L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBaseTreeEntity baseTreeEntity = new QBaseTreeEntity("baseTreeEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final SetPath<BaseTreeEntity<?>, QBaseTreeEntity> children = this.<BaseTreeEntity<?>, QBaseTreeEntity>createSet("children", BaseTreeEntity.class, QBaseTreeEntity.class, PathInits.DIRECT2);

    public final BooleanPath hasChildren = createBoolean("hasChildren");

    //inherited
    public final StringPath id = _super.id;

    public final StringPath levelIds = createString("levelIds");

    public final StringPath levelNames = createString("levelNames");

    public final QBaseTreeEntity parent;

    public final NumberPath<Long> sort = createNumber("sort", Long.class);

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseTreeEntity(String variable) {
        this((Class) BaseTreeEntity.class, forVariable(variable), INITS);
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseTreeEntity(Path<? extends BaseTreeEntity> path) {
        this((Class) path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBaseTreeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseTreeEntity(PathMetadata metadata, PathInits inits) {
        this((Class) BaseTreeEntity.class, metadata, inits);
    }

    public QBaseTreeEntity(Class<? extends BaseTreeEntity<?>> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QBaseTreeEntity(forProperty("parent"), inits.get("parent")) : null;
    }

}

