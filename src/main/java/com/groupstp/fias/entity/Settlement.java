package com.groupstp.fias.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;

@JmixEntity
@Entity(name = "fias_Settlement")
public class Settlement extends FiasEntity {
    private static final long serialVersionUID = -1390279436283538444L;
}