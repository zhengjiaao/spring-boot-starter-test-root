package com.zja.entitys.audit;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseAuditingEntity is a Querydsl query type for BaseAuditingEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseAuditingEntity extends EntityPathBase<BaseAuditingEntity> {

    private static final long serialVersionUID = -1211377194L;

    public static final QBaseAuditingEntity baseAuditingEntity = new QBaseAuditingEntity("baseAuditingEntity");

    public final com.zja.entitys.base.QBaseEntity _super = new com.zja.entitys.base.QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final StringPath createUserId = createString("createUserId");

    //inherited
    public final StringPath id = _super.id;

    public final DateTimePath<java.time.LocalDateTime> updateTime = createDateTime("updateTime", java.time.LocalDateTime.class);

    public final StringPath updateUserId = createString("updateUserId");

    public QBaseAuditingEntity(String variable) {
        super(BaseAuditingEntity.class, forVariable(variable));
    }

    public QBaseAuditingEntity(Path<? extends BaseAuditingEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseAuditingEntity(PathMetadata metadata) {
        super(BaseAuditingEntity.class, metadata);
    }

}

