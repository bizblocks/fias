package com.groupstp.fias.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;

@JmixEntity
@NamePattern("%s|name")
@Entity(name = "fias_Region")
public class Region extends FiasEntity {
    private static final long serialVersionUID = 4161402112705818548L;


}