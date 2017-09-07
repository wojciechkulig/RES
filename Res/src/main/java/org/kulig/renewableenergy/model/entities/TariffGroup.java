package org.kulig.renewableenergy.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "load_profiles")
@Component
public class TariffGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@ElementCollection
	@CollectionTable(name = "hourly_Energy_Consumption", joinColumns = @JoinColumn(name = "load_profiles_id"))
	private List<HourlyEnergyConsumption> hourlyEnergyConsumption = new ArrayList<HourlyEnergyConsumption>(8760);

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

	public List<HourlyEnergyConsumption> getHourlyEnergyConsumption() {
		return hourlyEnergyConsumption;
	}

	public void setHourlyEnergyConsumption(List<HourlyEnergyConsumption> hourlyEnergyConsumption) {
		this.hourlyEnergyConsumption = hourlyEnergyConsumption;
	}

	

}
