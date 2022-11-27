package com.groupstp.fias.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "FIAS_ADDRESS")
@Entity(name = "fias_Address")
@NamePattern("%s|srcAddress")
public class Address extends StandardEntity {
    private static final long serialVersionUID = 6928808408418198200L;

    @NotNull
    @Column(name = "SRC_ADDRESS", nullable = false, unique = true, length = 1024)
    protected String srcAddress;

    @Column(name = "NORM_ADDRESS")
    @Lob
    protected String normAddress;

    @ManyToMany
    @JoinTable(name = "FIAS_ADDRESS_HOUSE_LINK",
            joinColumns = @JoinColumn(name = "ADDRESS_ID"),
            inverseJoinColumns = @JoinColumn(name = "HOUSE_ID"))
    protected List<House> house;

    @JoinTable(name = "FIAS_ADDRESS_FIAS_ENTITY_LINK",
            joinColumns = @JoinColumn(name = "ADDRESS_ID"),
            inverseJoinColumns = @JoinColumn(name = "FIAS_ENTITY_ID"))
    @ManyToMany
    protected List<FiasEntity> fiasEntity;

    @ManyToMany
    @JoinTable(name = "FIAS_ADDRESS_STEAD_LINK",
            joinColumns = @JoinColumn(name = "ADDRESS_ID"),
            inverseJoinColumns = @JoinColumn(name = "STEAD_ID"))
    protected List<Stead> stead;

    @Column(name = "HOUSECOUNTER")
    protected Integer houseCounter;

    @Column(name = "STEADCOUNTER")
    protected Integer steadCounter;

    @Column(name = "CADASTRAL_NUMBER", length = 30)
    protected String cadastralNumber;

    public String getCadastralNumber() {
        return cadastralNumber;
    }

    public void setCadastralNumber(String cadastralNumber) {
        this.cadastralNumber = cadastralNumber;
    }

    public Integer getSteadCounter() {
        return steadCounter;
    }

    public void setSteadCounter(Integer steadCounter) {
        this.steadCounter = steadCounter;
    }

    public Integer getHouseCounter() {
        return houseCounter;
    }

    public void setHouseCounter(Integer houseCounter) {
        this.houseCounter = houseCounter;
    }

    public void setStead(List<Stead> stead) {
        this.stead = stead;
    }

    public List<Stead> getStead() {
        return stead;
    }

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