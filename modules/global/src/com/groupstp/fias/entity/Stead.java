package com.groupstp.fias.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Table(name = "FIAS_STEAD", indexes = {
        @Index(name = "IDX_FIAS_STEAD_NUMBER_PARENT_ADM", columnList = "NUMBER_, PARENT_ADM_ID"),
        @Index(name = "IDX_FIAS_STEAD_NUMBER_PARENT_MUN", columnList = "NUMBER_, PARENT_MUN_ID")
})
@Entity(name = "fias_Stead")
@NamePattern("%s|number")
public class Stead extends StandardEntity {
    private static final long serialVersionUID = 8491487611669490288L;

    @NotNull
    @Column(name = "GAR_ID", nullable = false, unique = true)
    protected Long garId;

    @Column(name = "NUMBER_", length = 250)
    protected String number;

    @Lookup(type = LookupType.SCREEN, actions = {"lookup", "open", "clear"})
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ADM_ID")
    protected FiasEntity parentAdm;

    @Lookup(type = LookupType.SCREEN, actions = {"lookup", "open", "clear"})
    @OnDeleteInverse(DeletePolicy.DENY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_MUN_ID")
    protected FiasEntity parentMun;

    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    protected Date startdate;

    @Temporal(TemporalType.DATE)
    @Column(name = "ENDDATE")
    protected Date enddate;

    @Temporal(TemporalType.DATE)
    @Column(name = "UPDATEDATE")
    protected Date updatedate;

    @Column(name = "ISACTUAL")
    protected Boolean isactual;

    @Column(name = "ISACTIVE")
    protected Boolean isactive;

    @Column(name = "ADDRESS_LEVEL")
    protected Integer addressLevel;

    @JoinTable(name = "FIAS_ADDRESS_STEAD_LINK",
            joinColumns = @JoinColumn(name = "STEAD_ID"),
            inverseJoinColumns = @JoinColumn(name = "ADDRESS_ID"))
    @OnDelete(DeletePolicy.CASCADE)
    @ManyToMany
    protected List<Address> addresses;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setAddressLevel(Integer addressLevel) {
        this.addressLevel = addressLevel;
    }

    public Integer getAddressLevel() {
        return addressLevel;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Boolean getIsactual() {
        return isactual;
    }

    public void setIsactual(Boolean isactual) {
        this.isactual = isactual;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getStartdate() {
        return startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public FiasEntity getParentMun() {
        return parentMun;
    }

    public void setParentMun(FiasEntity parentMun) {
        this.parentMun = parentMun;
    }

    public FiasEntity getParentAdm() {
        return parentAdm;
    }

    public void setParentAdm(FiasEntity parentAdm) {
        this.parentAdm = parentAdm;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getGarId() {
        return garId;
    }

    public void setGarId(Long garId) {
        this.garId = garId;
    }
}