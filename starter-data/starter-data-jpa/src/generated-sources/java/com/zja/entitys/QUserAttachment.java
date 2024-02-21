package com.zja.entitys;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserAttachment is a Querydsl query type for UserAttachment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserAttachment extends EntityPathBase<UserAttachment> {

    private static final long serialVersionUID = 1716531222L;

    public static final QUserAttachment userAttachment = new QUserAttachment("userAttachment");

    public final com.zja.entitys.base.QBaseEntity _super = new com.zja.entitys.base.QBaseEntity(this);

    public final StringPath email = createString("email");

    //inherited
    public final StringPath id = _super.id;

    public final StringPath mobilePhone = createString("mobilePhone");

    public final StringPath position = createString("position");

    public QUserAttachment(String variable) {
        super(UserAttachment.class, forVariable(variable));
    }

    public QUserAttachment(Path<? extends UserAttachment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserAttachment(PathMetadata metadata) {
        super(UserAttachment.class, metadata);
    }

}

