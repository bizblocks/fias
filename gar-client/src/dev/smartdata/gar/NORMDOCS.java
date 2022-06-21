//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2022.06.20 às 03:10:31 PM IRKT 
//


package dev.smartdata.gar;

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
 *         &lt;element name="NORMDOC" maxOccurs="unbounded" minOccurs="0">
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
 *                 &lt;attribute name="NAME" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="8000"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="DATE" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                 &lt;attribute name="NUMBER" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="150"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="TYPE" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                       &lt;totalDigits value="10"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="KIND" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                       &lt;totalDigits value="10"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="UPDATEDATE" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                 &lt;attribute name="ORGNAME">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="0"/>
 *                       &lt;maxLength value="255"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="REGNUM">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="0"/>
 *                       &lt;maxLength value="100"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="REGDATE" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                 &lt;attribute name="ACCDATE" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                 &lt;attribute name="COMMENT">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="0"/>
 *                       &lt;maxLength value="8000"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
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
    "normdoc"
})
@XmlRootElement(name = "NORMDOCS")
public class NORMDOCS {

    @XmlElement(name = "NORMDOC")
    protected List<NORMDOCS.NORMDOC> normdoc;

    /**
     * Gets the value of the normdoc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the normdoc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNORMDOC().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NORMDOCS.NORMDOC }
     * 
     * 
     */
    public List<NORMDOCS.NORMDOC> getNORMDOC() {
        if (normdoc == null) {
            normdoc = new ArrayList<NORMDOCS.NORMDOC>();
        }
        return this.normdoc;
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
     *       &lt;attribute name="NAME" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;maxLength value="8000"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="DATE" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *       &lt;attribute name="NUMBER" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;maxLength value="150"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="TYPE" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *             &lt;totalDigits value="10"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="KIND" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *             &lt;totalDigits value="10"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="UPDATEDATE" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *       &lt;attribute name="ORGNAME">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="0"/>
     *             &lt;maxLength value="255"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="REGNUM">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="0"/>
     *             &lt;maxLength value="100"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="REGDATE" type="{http://www.w3.org/2001/XMLSchema}date" />
     *       &lt;attribute name="ACCDATE" type="{http://www.w3.org/2001/XMLSchema}date" />
     *       &lt;attribute name="COMMENT">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="0"/>
     *             &lt;maxLength value="8000"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class NORMDOC {

        @XmlAttribute(name = "ID", required = true)
        protected long id;
        @XmlAttribute(name = "NAME", required = true)
        protected String name;
        @XmlAttribute(name = "DATE", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar date;
        @XmlAttribute(name = "NUMBER", required = true)
        protected String number;
        @XmlAttribute(name = "TYPE", required = true)
        protected BigInteger type;
        @XmlAttribute(name = "KIND", required = true)
        protected BigInteger kind;
        @XmlAttribute(name = "UPDATEDATE", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar updatedate;
        @XmlAttribute(name = "ORGNAME")
        protected String orgname;
        @XmlAttribute(name = "REGNUM")
        protected String regnum;
        @XmlAttribute(name = "REGDATE")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar regdate;
        @XmlAttribute(name = "ACCDATE")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar accdate;
        @XmlAttribute(name = "COMMENT")
        protected String comment;

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
         * Obtém o valor da propriedade name.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNAME() {
            return name;
        }

        /**
         * Define o valor da propriedade name.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNAME(String value) {
            this.name = value;
        }

        /**
         * Obtém o valor da propriedade date.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDATE() {
            return date;
        }

        /**
         * Define o valor da propriedade date.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDATE(XMLGregorianCalendar value) {
            this.date = value;
        }

        /**
         * Obtém o valor da propriedade number.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNUMBER() {
            return number;
        }

        /**
         * Define o valor da propriedade number.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNUMBER(String value) {
            this.number = value;
        }

        /**
         * Obtém o valor da propriedade type.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getTYPE() {
            return type;
        }

        /**
         * Define o valor da propriedade type.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setTYPE(BigInteger value) {
            this.type = value;
        }

        /**
         * Obtém o valor da propriedade kind.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getKIND() {
            return kind;
        }

        /**
         * Define o valor da propriedade kind.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setKIND(BigInteger value) {
            this.kind = value;
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
         * Obtém o valor da propriedade orgname.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getORGNAME() {
            return orgname;
        }

        /**
         * Define o valor da propriedade orgname.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setORGNAME(String value) {
            this.orgname = value;
        }

        /**
         * Obtém o valor da propriedade regnum.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREGNUM() {
            return regnum;
        }

        /**
         * Define o valor da propriedade regnum.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREGNUM(String value) {
            this.regnum = value;
        }

        /**
         * Obtém o valor da propriedade regdate.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getREGDATE() {
            return regdate;
        }

        /**
         * Define o valor da propriedade regdate.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setREGDATE(XMLGregorianCalendar value) {
            this.regdate = value;
        }

        /**
         * Obtém o valor da propriedade accdate.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getACCDATE() {
            return accdate;
        }

        /**
         * Define o valor da propriedade accdate.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setACCDATE(XMLGregorianCalendar value) {
            this.accdate = value;
        }

        /**
         * Obtém o valor da propriedade comment.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCOMMENT() {
            return comment;
        }

        /**
         * Define o valor da propriedade comment.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCOMMENT(String value) {
            this.comment = value;
        }

    }

}
