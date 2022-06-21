//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2022.06.21 às 01:10:26 PM IRKT 
//


package dev.smartdata.gar.ADMHIERARCHY;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ITEM" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="ID" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *                       &lt;totalDigits value="19"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="OBJECTID" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *                       &lt;totalDigits value="19"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="PARENTOBJID">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *                       &lt;totalDigits value="19"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="CHANGEID" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *                       &lt;totalDigits value="19"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="REGIONCODE">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="4"/>
 *                       &lt;pattern value="[0-9]{1,4}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="AREACODE">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="4"/>
 *                       &lt;pattern value="[0-9]{1,4}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="CITYCODE">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="4"/>
 *                       &lt;pattern value="[0-9]{1,4}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="PLACECODE">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="4"/>
 *                       &lt;pattern value="[0-9]{1,4}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="PLANCODE">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="4"/>
 *                       &lt;pattern value="[0-9]{1,4}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="STREETCODE">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="4"/>
 *                       &lt;pattern value="[0-9]{1,4}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="PREVID">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *                       &lt;totalDigits value="19"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="NEXTID">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *                       &lt;totalDigits value="19"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="UPDATEDATE" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                 &lt;attribute name="STARTDATE" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                 &lt;attribute name="ENDDATE" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                 &lt;attribute name="ISACTIVE" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                       &lt;enumeration value="0"/>
 *                       &lt;enumeration value="1"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="PATH" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "item"
})
@XmlRootElement(name = "ITEMS")
public class ITEMS {

    @XmlElement(name = "ITEM", required = true)
    protected List<ITEMS.ITEM> item;

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getITEM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ITEMS.ITEM }
     * 
     * 
     */
    public List<ITEMS.ITEM> getITEM() {
        if (item == null) {
            item = new ArrayList<ITEMS.ITEM>();
        }
        return this.item;
    }


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="ID" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
     *             &lt;totalDigits value="19"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="OBJECTID" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
     *             &lt;totalDigits value="19"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="PARENTOBJID">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
     *             &lt;totalDigits value="19"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="CHANGEID" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
     *             &lt;totalDigits value="19"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="REGIONCODE">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;maxLength value="4"/>
     *             &lt;pattern value="[0-9]{1,4}"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="AREACODE">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;maxLength value="4"/>
     *             &lt;pattern value="[0-9]{1,4}"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="CITYCODE">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;maxLength value="4"/>
     *             &lt;pattern value="[0-9]{1,4}"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="PLACECODE">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;maxLength value="4"/>
     *             &lt;pattern value="[0-9]{1,4}"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="PLANCODE">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;maxLength value="4"/>
     *             &lt;pattern value="[0-9]{1,4}"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="STREETCODE">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;maxLength value="4"/>
     *             &lt;pattern value="[0-9]{1,4}"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="PREVID">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
     *             &lt;totalDigits value="19"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="NEXTID">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
     *             &lt;totalDigits value="19"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="UPDATEDATE" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *       &lt;attribute name="STARTDATE" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *       &lt;attribute name="ENDDATE" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *       &lt;attribute name="ISACTIVE" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *             &lt;enumeration value="0"/>
     *             &lt;enumeration value="1"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="PATH" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ITEM {

        @XmlAttribute(name = "ID", required = true)
        protected long id;
        @XmlAttribute(name = "OBJECTID", required = true)
        protected long objectid;
        @XmlAttribute(name = "PARENTOBJID")
        protected Long parentobjid;
        @XmlAttribute(name = "CHANGEID", required = true)
        protected long changeid;
        @XmlAttribute(name = "REGIONCODE")
        protected String regioncode;
        @XmlAttribute(name = "AREACODE")
        protected String areacode;
        @XmlAttribute(name = "CITYCODE")
        protected String citycode;
        @XmlAttribute(name = "PLACECODE")
        protected String placecode;
        @XmlAttribute(name = "PLANCODE")
        protected String plancode;
        @XmlAttribute(name = "STREETCODE")
        protected String streetcode;
        @XmlAttribute(name = "PREVID")
        protected Long previd;
        @XmlAttribute(name = "NEXTID")
        protected Long nextid;
        @XmlAttribute(name = "UPDATEDATE", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar updatedate;
        @XmlAttribute(name = "STARTDATE", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar startdate;
        @XmlAttribute(name = "ENDDATE", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar enddate;
        @XmlAttribute(name = "ISACTIVE", required = true)
        protected BigInteger isactive;
        @XmlAttribute(name = "PATH", required = true)
        @XmlSchemaType(name = "anySimpleType")
        protected String path;

        /**
         * Obtém o valor da propriedade id.
         * 
         */
        public long getID() {
            return id;
        }

        /**
         * Define o valor da propriedade id.
         * 
         */
        public void setID(long value) {
            this.id = value;
        }

        /**
         * Obtém o valor da propriedade objectid.
         * 
         */
        public long getOBJECTID() {
            return objectid;
        }

        /**
         * Define o valor da propriedade objectid.
         * 
         */
        public void setOBJECTID(long value) {
            this.objectid = value;
        }

        /**
         * Obtém o valor da propriedade parentobjid.
         * 
         * @return
         *     possible object is
         *     {@link Long }
         *     
         */
        public Long getPARENTOBJID() {
            return parentobjid;
        }

        /**
         * Define o valor da propriedade parentobjid.
         * 
         * @param value
         *     allowed object is
         *     {@link Long }
         *     
         */
        public void setPARENTOBJID(Long value) {
            this.parentobjid = value;
        }

        /**
         * Obtém o valor da propriedade changeid.
         * 
         */
        public long getCHANGEID() {
            return changeid;
        }

        /**
         * Define o valor da propriedade changeid.
         * 
         */
        public void setCHANGEID(long value) {
            this.changeid = value;
        }

        /**
         * Obtém o valor da propriedade regioncode.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREGIONCODE() {
            return regioncode;
        }

        /**
         * Define o valor da propriedade regioncode.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREGIONCODE(String value) {
            this.regioncode = value;
        }

        /**
         * Obtém o valor da propriedade areacode.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAREACODE() {
            return areacode;
        }

        /**
         * Define o valor da propriedade areacode.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAREACODE(String value) {
            this.areacode = value;
        }

        /**
         * Obtém o valor da propriedade citycode.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCITYCODE() {
            return citycode;
        }

        /**
         * Define o valor da propriedade citycode.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCITYCODE(String value) {
            this.citycode = value;
        }

        /**
         * Obtém o valor da propriedade placecode.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPLACECODE() {
            return placecode;
        }

        /**
         * Define o valor da propriedade placecode.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPLACECODE(String value) {
            this.placecode = value;
        }

        /**
         * Obtém o valor da propriedade plancode.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPLANCODE() {
            return plancode;
        }

        /**
         * Define o valor da propriedade plancode.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPLANCODE(String value) {
            this.plancode = value;
        }

        /**
         * Obtém o valor da propriedade streetcode.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTREETCODE() {
            return streetcode;
        }

        /**
         * Define o valor da propriedade streetcode.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTREETCODE(String value) {
            this.streetcode = value;
        }

        /**
         * Obtém o valor da propriedade previd.
         * 
         * @return
         *     possible object is
         *     {@link Long }
         *     
         */
        public Long getPREVID() {
            return previd;
        }

        /**
         * Define o valor da propriedade previd.
         * 
         * @param value
         *     allowed object is
         *     {@link Long }
         *     
         */
        public void setPREVID(Long value) {
            this.previd = value;
        }

        /**
         * Obtém o valor da propriedade nextid.
         * 
         * @return
         *     possible object is
         *     {@link Long }
         *     
         */
        public Long getNEXTID() {
            return nextid;
        }

        /**
         * Define o valor da propriedade nextid.
         * 
         * @param value
         *     allowed object is
         *     {@link Long }
         *     
         */
        public void setNEXTID(Long value) {
            this.nextid = value;
        }

        /**
         * Obtém o valor da propriedade updatedate.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getUPDATEDATE() {
            return updatedate;
        }

        /**
         * Define o valor da propriedade updatedate.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setUPDATEDATE(XMLGregorianCalendar value) {
            this.updatedate = value;
        }

        /**
         * Obtém o valor da propriedade startdate.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getSTARTDATE() {
            return startdate;
        }

        /**
         * Define o valor da propriedade startdate.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setSTARTDATE(XMLGregorianCalendar value) {
            this.startdate = value;
        }

        /**
         * Obtém o valor da propriedade enddate.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getENDDATE() {
            return enddate;
        }

        /**
         * Define o valor da propriedade enddate.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setENDDATE(XMLGregorianCalendar value) {
            this.enddate = value;
        }

        /**
         * Obtém o valor da propriedade isactive.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getISACTIVE() {
            return isactive;
        }

        /**
         * Define o valor da propriedade isactive.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setISACTIVE(BigInteger value) {
            this.isactive = value;
        }

        /**
         * Obtém o valor da propriedade path.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPATH() {
            return path;
        }

        /**
         * Define o valor da propriedade path.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPATH(String value) {
            this.path = value;
        }

    }

}
