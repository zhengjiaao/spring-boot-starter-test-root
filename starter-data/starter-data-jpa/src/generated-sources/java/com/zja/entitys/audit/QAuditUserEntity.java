package com.zja.entitys.audit;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuditUserEntity is a Querydsl query type for AuditUserEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAuditUserEntity extends EntityPathBase<AuditUserEntity> {

    private static final long serialVersionUID = 1966466030L;

    public static final QAuditUserEntity auditUserEntity = new QAuditUserEntity("auditUserEntity");

    public final QBaseAuditingEntity _super = new QBaseAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    //inherited
    public final StringPath createUserId = _super.createUserId;

    //inherited
    public final StringPath id = _super.id;

    public final StringPath password = createString("password");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    //inherited
    public final StringPath updateUserId = _super.updateUserId;

    public final StringPath username = createString("username");

    public QAuditUserEntity(String variable) {
        super(AuditUserEntity.class, forVariable(variable));
    }

    public QAuditUserEntity(Path<? extends AuditUserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuditUserEntity(PathMetadata metadata) {
        super(AuditUserEntity.class, metadata);
    }

}

