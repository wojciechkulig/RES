package org.kulig.renewableenergy.model.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Weather {
	@Column(name="H")
	private int H;
	
	private int M;
	private int D;
	private int UTC_H;
	private double DBT;
	private int RH;
	private double HR;
	private double WS;
	private double WD;
	private double ITH;
	private double IDH;
	private double ISH;
	private double TSKY;
	@Transient
	private double irradiance;
	
	private double N_0;
	
	private double N_30;
	private double NE_30;
	private double E_30;
	private double SE_30;
	private double S_30;
	private double SW_30;
	private double W_30;
	private double NW_30;
	
	private double N_45;
	private double NE_45;
	private double E_45;
	private double SE_45;
	private double S_45;
	private double SW_45;
	private double W_45;
	private double NW_45;
	
	private double N_60;
	private double NE_60;
	private double E_60;
	private double SE_60;
	private double S_60;
	private double SW_60;
	private double W_60;
	private double NW_60;
	
	private double N_90;
	private double NE_90;
	private double E_90;
	private double SE_90;
	private double S_90;
	private double SW_90;
	private double W_90;
	private double NW_90;
	
	public int getH() {
		return H;
	}
	public void setH(int h) {
		H = h;
	}
	public int getM() {
		return M;
	}
	public void setM(int m) {
		M = m;
	}
	public int getD() {
		return D;
	}
	public void setD(int d) {
		D = d;
	}
	public int getUTC_H() {
		return UTC_H;
	}
	public void setUTC_H(int uTC_H) {
		UTC_H = uTC_H;
	}
	public double getDBT() {
		return DBT;
	}
	public void setDBT(double dBT) {
		DBT = dBT;
	}
	public int getRH() {
		return RH;
	}
	public void setRH(int rH) {
		RH = rH;
	}
	public double getHR() {
		return HR;
	}
	public void setHR(double hR) {
		HR = hR;
	}
	public double getWS() {
		return WS;
	}
	public void setWS(double wS) {
		WS = wS;
	}
	public double getWD() {
		return WD;
	}
	public void setWD(double wD) {
		WD = wD;
	}
	public double getITH() {
		return ITH;
	}
	public void setITH(double iTH) {
		ITH = iTH;
	}
	public double getIDH() {
		return IDH;
	}
	public void setIDH(double iDH) {
		IDH = iDH;
	}
	public double getISH() {
		return ISH;
	}
	public void setISH(double iSH) {
		ISH = iSH;
	}
	public double getTSKY() {
		return TSKY;
	}
	public void setTSKY(double tSKY) {
		TSKY = tSKY;
	}
	public double getIrradiance() {
		return irradiance;
	}
	public void setIrradiance(double irradiance) {
		this.irradiance = irradiance;
	}
	public double getN_0() {
		return N_0;
	}
	public void setN_0(double n_0) {
		N_0 = n_0;
	}
	public double getN_30() {
		return N_30;
	}
	public void setN_30(double n_30) {
		N_30 = n_30;
	}
	public double getNE_30() {
		return NE_30;
	}
	public void setNE_30(double nE_30) {
		NE_30 = nE_30;
	}
	public double getE_30() {
		return E_30;
	}
	public void setE_30(double e_30) {
		E_30 = e_30;
	}
	public double getSE_30() {
		return SE_30;
	}
	public void setSE_30(double sE_30) {
		SE_30 = sE_30;
	}
	public double getS_30() {
		return S_30;
	}
	public void setS_30(double s_30) {
		S_30 = s_30;
	}
	public double getSW_30() {
		return SW_30;
	}
	public void setSW_30(double sW_30) {
		SW_30 = sW_30;
	}
	public double getW_30() {
		return W_30;
	}
	public void setW_30(double w_30) {
		W_30 = w_30;
	}
	public double getNW_30() {
		return NW_30;
	}
	public void setNW_30(double nW_30) {
		NW_30 = nW_30;
	}
	public double getN_45() {
		return N_45;
	}
	public void setN_45(double n_45) {
		N_45 = n_45;
	}
	public double getNE_45() {
		return NE_45;
	}
	public void setNE_45(double nE_45) {
		NE_45 = nE_45;
	}
	public double getE_45() {
		return E_45;
	}
	public void setE_45(double e_45) {
		E_45 = e_45;
	}
	public double getSE_45() {
		return SE_45;
	}
	public void setSE_45(double sE_45) {
		SE_45 = sE_45;
	}
	public double getS_45() {
		return S_45;
	}
	public void setS_45(double s_45) {
		S_45 = s_45;
	}
	public double getSW_45() {
		return SW_45;
	}
	public void setSW_45(double sW_45) {
		SW_45 = sW_45;
	}
	public double getW_45() {
		return W_45;
	}
	public void setW_45(double w_45) {
		W_45 = w_45;
	}
	public double getNW_45() {
		return NW_45;
	}
	public void setNW_45(double nW_45) {
		NW_45 = nW_45;
	}
	public double getN_60() {
		return N_60;
	}
	public void setN_60(double n_60) {
		N_60 = n_60;
	}
	public double getNE_60() {
		return NE_60;
	}
	public void setNE_60(double nE_60) {
		NE_60 = nE_60;
	}
	public double getE_60() {
		return E_60;
	}
	public void setE_60(double e_60) {
		E_60 = e_60;
	}
	public double getSE_60() {
		return SE_60;
	}
	public void setSE_60(double sE_60) {
		SE_60 = sE_60;
	}
	public double getS_60() {
		return S_60;
	}
	public void setS_60(double s_60) {
		S_60 = s_60;
	}
	public double getSW_60() {
		return SW_60;
	}
	public void setSW_60(double sW_60) {
		SW_60 = sW_60;
	}
	public double getW_60() {
		return W_60;
	}
	public void setW_60(double w_60) {
		W_60 = w_60;
	}
	public double getNW_60() {
		return NW_60;
	}
	public void setNW_60(double nW_60) {
		NW_60 = nW_60;
	}
	public double getN_90() {
		return N_90;
	}
	public void setN_90(double n_90) {
		N_90 = n_90;
	}
	public double getNE_90() {
		return NE_90;
	}
	public void setNE_90(double nE_90) {
		NE_90 = nE_90;
	}
	public double getE_90() {
		return E_90;
	}
	public void setE_90(double e_90) {
		E_90 = e_90;
	}
	public double getSE_90() {
		return SE_90;
	}
	public void setSE_90(double sE_90) {
		SE_90 = sE_90;
	}
	public double getS_90() {
		return S_90;
	}
	public void setS_90(double s_90) {
		S_90 = s_90;
	}
	public double getSW_90() {
		return SW_90;
	}
	public void setSW_90(double sW_90) {
		SW_90 = sW_90;
	}
	public double getW_90() {
		return W_90;
	}
	public void setW_90(double w_90) {
		W_90 = w_90;
	}
	public double getNW_90() {
		return NW_90;
	}
	public void setNW_90(double nW_90) {
		NW_90 = nW_90;
	}
	public void setWindSpeedOnGivenHeight(double instalationHeight){
		setWS(getWS()*Math.pow(instalationHeight/10.0, 0.2));
	}
	public void setIrradiance(String azimuth,double inclination){
		if(inclination ==0.0){
			setIrradiance(getN_0());
		}
		if(inclination == 30.0){
			if(azimuth.equals("N")){
				setIrradiance(getN_30());
			}
			if(azimuth.equals("NE")){
				setIrradiance(getNE_30());
			}
			if(azimuth.equals("E")){
				setIrradiance(getE_30());
			}
			if(azimuth.equals("SE")){
				setIrradiance(getSE_30());
			}
			if(azimuth.equals("S")){
				setIrradiance(getS_30());
			}
			if(azimuth.equals("SW")){
				setIrradiance(getSW_30());
			}
			if(azimuth.equals("W")){
				setIrradiance(getW_30());
			}
			if(azimuth.equals("NW")){
				setIrradiance(getNW_30());
			}
		}
		if(inclination == 45.0){
			if(azimuth.equals("N")){
				setIrradiance(getN_45());
			}
			if(azimuth.equals("NE")){
				setIrradiance(getNE_45());
			}
			if(azimuth.equals("E")){
				setIrradiance(getE_45());
			}
			if(azimuth.equals("SE")){
				setIrradiance(getSE_45());
			}
			if(azimuth.equals("S")){
				setIrradiance(getS_45());
			}
			if(azimuth.equals("SW")){
				setIrradiance(getSW_45());
			}
			if(azimuth.equals("W")){
				setIrradiance(getW_45());
			}
			if(azimuth.equals("NW")){
				setIrradiance(getNW_45());
			}
		}
		if(inclination == 60.0){
			if(azimuth.equals("N")){
				setIrradiance(getN_60());
			}
			if(azimuth.equals("NE")){
				setIrradiance(getNE_60());
			}
			if(azimuth.equals("E")){
				setIrradiance(getE_60());
			}
			if(azimuth.equals("SE")){
				setIrradiance(getSE_60());
			}
			if(azimuth.equals("S")){
				setIrradiance(getS_60());
			}
			if(azimuth.equals("SW")){
				setIrradiance(getSW_60());
			}
			if(azimuth.equals("W")){
				setIrradiance(getW_60());
			}
			if(azimuth.equals("NW")){
				setIrradiance(getNW_60());
			}
		}
		if(inclination == 90.0){
			if(azimuth.equals("N")){
				setIrradiance(getN_90());
			}
			if(azimuth.equals("NE")){
				setIrradiance(getNE_90());
			}
			if(azimuth.equals("E")){
				setIrradiance(getE_90());
			}
			if(azimuth.equals("SE")){
				setIrradiance(getSE_90());
			}
			if(azimuth.equals("S")){
				setIrradiance(getS_90());
			}
			if(azimuth.equals("SW")){
				setIrradiance(getSW_90());
			}
			if(azimuth.equals("W")){
				setIrradiance(getW_90());
			}
			if(azimuth.equals("NW")){
				setIrradiance(getNW_90());
			}
		}
		
		irradiance = irradiance/1000;
		
	}
	
	
	
	
	

}
