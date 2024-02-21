package com.zja.entitys.relationship.oneToOne;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOffice is a Querydsl query type for Office
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOffice extends EntityPathBase<Office> {

    private static final long serialVersionUID = -2128671561L;

    public static final QOffice office = new QOffice("office");

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final StringPath officeName = createString("officeName");

    public QOffice(String variable) {
        super(Office.class, forVariable(variable));
    }

    public QOffice(Path<? extends Office> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOffice(PathMetadata metadata) {
        super(Office.class, metadata);
    }

}

