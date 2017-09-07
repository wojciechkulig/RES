package org.kulig.renewableenergy.model.entities;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="PV_Modules")
public class PvModule {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(name="module_length")
	private double moduleLength;
	@Column(name="module_height")
	private double moduleHeight;
	@Column(name="module_efficiency_stc")
	private double moduleEfficiencySTC;
	@Column(name="vmpp_stc")
	private double vmppSTC;
	@Column(name="impp_stc")
	private double imppSTC;
	@Column(name="vmpp_noct")
	private double vmppNOCT;
	@Column(name="impp_noct")
	private double imppNOCT;
	@Column(name="voc_stc")
	private double vocSTC;
	@Column(name="voc_noct")
	private double vocNOCT;
	@Column(name="isc_stc")
	private double iscSTC;
	@Column(name="isc_noct")
	private double iscNOCT;
	@Column(name="module_surface")
	private double moduleSurface;
	@Column(name="noct")
	private double NOCT;
	@Column(name="temperature_coefficient_of_voc")
	private double temperatureCoefficientOfVoc;
	@Column(name="temperature_coefficient_of_isc")
	private double temperatureCoefficientOfIsc;
	@Column(name="temperature_coefficient_of_pmax")
	private double temperatureCoefficientOfPmax;
	@Column(name="nominal_power")
	private double nominalPower;
	
	@ElementCollection
	@CollectionTable(name = "PV_Module_performance_guarantees", joinColumns = @JoinColumn(name="PV_Modules_id"))
	private List<PvModulePeformanceGuarantee> pvModulePowerOutputWarrantyPeriods;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getModuleLength() {
		return moduleLength;
	}

	public void setModuleLength(double moduleLength) {
		this.moduleLength = moduleLength;
	}

	public double getModuleHeight() {
		return moduleHeight;
	}

	public void setModuleHeight(double moduleHeight) {
		this.moduleHeight = moduleHeight;
	}

	public double getModuleEfficiencySTC() {
		return moduleEfficiencySTC;
	}

	public void setModuleEfficiencySTC(double moduleEfficiencySTC) {
		this.moduleEfficiencySTC = moduleEfficiencySTC;
	}

	public double getVmppSTC() {
		return vmppSTC;
	}

	public void setVmppSTC(double vmppSTC) {
		this.vmppSTC = vmppSTC;
	}

	public double getImppSTC() {
		return imppSTC;
	}

	public void setImppSTC(double imppSTC) {
		this.imppSTC = imppSTC;
	}

	public double getVmppNOCT() {
		return vmppNOCT;
	}

	public void setVmppNOCT(double vmppNOCT) {
		this.vmppNOCT = vmppNOCT;
	}

	public double getImppNOCT() {
		return imppNOCT;
	}

	public void setImppNOCT(double imppNOCT) {
		this.imppNOCT = imppNOCT;
	}

	public double getVocSTC() {
		return vocSTC;
	}

	public void setVocSTC(double vocSTC) {
		this.vocSTC = vocSTC;
	}

	public double getVocNOCT() {
		return vocNOCT;
	}

	public void setVocNOCT(double vocNOCT) {
		this.vocNOCT = vocNOCT;
	}

	public double getIscSTC() {
		return iscSTC;
	}

	public void setIscSTC(double iscSTC) {
		this.iscSTC = iscSTC;
	}

	public double getIscNOCT() {
		return iscNOCT;
	}

	public void setIscNOCT(double iscNOCT) {
		this.iscNOCT = iscNOCT;
	}

	public double getModuleSurface() {
		return moduleSurface;
	}

	public void setModuleSurface(double moduleSurface) {
		this.moduleSurface = moduleSurface;
	}

	public double getNOCT() {
		return NOCT;
	}

	public void setNOCT(double nOCT) {
		NOCT = nOCT;
	}

	public double getTemperatureCoefficientOfVoc() {
		return temperatureCoefficientOfVoc;
	}

	public void setTemperatureCoefficientOfVoc(double temperatureCoefficientOfVoc) {
		this.temperatureCoefficientOfVoc = temperatureCoefficientOfVoc;
	}

	public double getTemperatureCoefficientOfIsc() {
		return temperatureCoefficientOfIsc;
	}

	public void setTemperatureCoefficientOfIsc(double temperatureCoefficientOfIsc) {
		this.temperatureCoefficientOfIsc = temperatureCoefficientOfIsc;
	}

	public double getTemperatureCoefficientOfPmax() {
		return temperatureCoefficientOfPmax;
	}

	public void setTemperatureCoefficientOfPmax(double temperatureCoefficientOfPmax) {
		this.temperatureCoefficientOfPmax = temperatureCoefficientOfPmax;
	}

	public List<PvModulePeformanceGuarantee> getPvModulePowerOutputWarrantyPeriods() {
		return pvModulePowerOutputWarrantyPeriods;
	}

	public void setPvModulePowerOutputWarrantyPeriods(
			List<PvModulePeformanceGuarantee> pvModulePowerOutputWarrantyPeriods) {
		this.pvModulePowerOutputWarrantyPeriods = pvModulePowerOutputWarrantyPeriods;
	}

	public double getNominalPower() {
		return nominalPower;
	}

	public void setNominalPower(double nominalPower) {
		this.nominalPower = nominalPower;
	}

	


	

}
