package com.groupstp.fias.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@JmixEntity
@Table(name = "FIAS_DIVISION_TAG", indexes = {
        @Index(name = "IDX_FIAS_DIVISION_TAG", columnList = "TAG")
})
@Entity(name = "fias_DivisionTag")
public class DivisionTag extends StandardEntity {
    private static final long serialVersionUID = 6836830840282249652L;

    @Column(name = "TAG")
    protected String tag;

    @Column(name = "TYPE_")
    protected String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}