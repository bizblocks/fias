package com.groupstp.fias.entity;

import com.groupstp.fias.entity.enums.FiasEntityOperationStatus;
import com.groupstp.fias.entity.enums.FiasEntityStatus;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.util.Date;

@NamePattern("%s %s|shortname,name")
@Table(name = "FIAS_FIAS_ENTITY", indexes = {
        @Index(name = "IDX_FIAS_FIAS_ENTITY", columnList = "CODE")
})
@Entity(name = "fias$FiasEntity")
public class FiasEntity extends StandardEntity {
    private static final long serialVersionUID = 5234139283100152959L;

    @Column(name = "NAME")
    protected String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    protected FiasEntity parentAdm;

    @Lookup(type = LookupType.SCREEN, actions = {"lookup", "open", "clear"})
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_MUN_ID")
    protected FiasEntity parentMun;

    @Column(name = "POSTAL_CODE", length = 6)
    protected String postalCode;

    @Column(name = "OFFNAME")
    protected String offname;

    @Column(name = "FORMALNAME")
    protected String formalname;

    @Lob
    @Column(name = "POSSIBLE_NAMES")
    protected String possibleNames;

    @Column(name = "CODE", unique = true)
    protected Long garId;

    @Column(name = "SHORTNAME", length = 50)
    protected String shortname;


    @Temporal(TemporalType.DATE)
    @Column(name = "UPDATEDATE")
    protected Date updatedate;

    @Column(name = "ACTSTATUS")
    protected Integer actstatus;

    @Column(name = "OPERSTATUS")
    protected Integer operstatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "STARTDATE")
    protected Date startdate;

    @Temporal(TemporalType.DATE)
    @Column(name = "ENDDATE")
    protected Date enddate;

    @Column(name = "PREV_ID")
    protected Long prevID;

    public FiasEntity getParentMun() {
        return parentMun;
    }

    public void setParentMun(FiasEntity parentMun) {
        this.parentMun = parentMun;
    }

    public void setPrevID(Long prevID) {
        this.prevID = prevID;
    }

    public Long getPrevID() {
        return prevID;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }


    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getStartdate() {
        return startdate;
    }


    public void setActstatus(FiasEntityStatus actstatus) {
        this.actstatus = actstatus == null ? null : actstatus.getId();
    }

    public FiasEntityStatus getActstatus() {
        return actstatus == null ? null : FiasEntityStatus.fromId(actstatus);
    }

    public void setOperstatus(FiasEntityOperationStatus operstatus) {
        this.operstatus = operstatus == null ? null : operstatus.getId();
    }

    public FiasEntityOperationStatus getOperstatus() {
        return operstatus == null ? null : FiasEntityOperationStatus.fromId(operstatus);
    }


    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }


    public void setParentAdm(FiasEntity parentAdm) {
        this.parentAdm = parentAdm;
    }

    public FiasEntity getParentAdm() {
        return parentAdm;
    }


    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOffname(String offname) {
        this.offname = offname;
    }

    public String getOffname() {
        return offname;
    }

    public void setFormalname(String formalname) {
        this.formalname = formalname;
    }

    public String getFormalname() {
        return formalname;
    }

    public void setPossibleNames(String possibleNames) {
        this.possibleNames = possibleNames;
    }

    public String getPossibleNames() {
        return possibleNames;
    }

    public void setGarId(Long garId) {
        this.garId = garId;
    }

    public Long getGarId() {
        return garId;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getShortname() {
        return shortname;
    }


}