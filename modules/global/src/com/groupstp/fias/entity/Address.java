package com.groupstp.fias.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "FIAS_ADDRESS")
@Entity(name = "fias_Address")
public class Address extends StandardEntity {
    private static final long serialVersionUID = 6928808408418198200L;

    @NotNull
    @Column(name = "SRC_ADDRESS", nullable = false, unique = true, length = 1024)
    protected String srcAddress;

    @Column(name = "NORM_ADDRESS", length = 1024)
    protected String normAddress;

    @ManyToMany
    @JoinTable(name = "FIAS_ADDRESS_HOUSE_LINK",
            joinColumns = @JoinColumn(name = "ADDRESS_ID"),
            inverseJoinColumns = @JoinColumn(name = "HOUSE_ID"))
    protected List<House> house;

    @JoinTable(name = "FIAS_ADDRESS_FIAS_ENTITY_LINK",
            joinColumns = @JoinColumn(name = "ADDRESS_ID"),
            inverseJoinColumns = @JoinColumn(name = "FIAS_ENTITY_ID"))
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @OnDelete(DeletePolicy.DENY)
    @ManyToMany
    protected List<FiasEntity> fiasEntity;

    public List<FiasEntity> getFiasEntity() {
        return fiasEntity;
    }

    public void setFiasEntity(List<FiasEntity> fiasEntity) {
        this.fiasEntity = fiasEntity;
    }

    public void setHouse(List<House> house) {
        this.house = house;
    }

    public List<House> getHouse() {
        return house;
    }

    public String getNormAddress() {
        return normAddress;
    }

    public void setNormAddress(String normAddress) {
        this.normAddress = normAddress;
    }

    public String getSrcAddress() {
        return srcAddress;
    }

    public void setSrcAddress(String srcAddress) {
        this.srcAddress = srcAddress;
    }
}