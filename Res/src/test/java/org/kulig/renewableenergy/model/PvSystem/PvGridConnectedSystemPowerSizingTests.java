package org.kulig.renewableenergy.model.PvSystem;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kulig.renewableenergy.model.PvSystem.PvGridConnectedSystemPowerSizing;
import org.kulig.renewableenergy.model.entities.PvModule;
import org.kulig.renewableenergy.model.entities.Weather;


public class PvGridConnectedSystemPowerSizingTests {

	private static PvGridConnectedSystemPowerSizing pvGridConnectedSystemPowerSizing;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		List<Weather> meteorologicalYear = getMeteorologicalYear();
		double annualEnergyConsumption = 3000;
		pvGridConnectedSystemPowerSizing = new PvGridConnectedSystemPowerSizing(meteorologicalYear,
				annualEnergyConsumption);
	}
	
	@Test
	public void testGetNumberOfPvModules(){
		PvModule pvModule = new PvModule();
		pvModule.setNominalPower(250);
		int numberOfPvModules = pvGridConnectedSystemPowerSizing.getNumberOfPvModules(pvModule);
		assertEquals(13,numberOfPvModules,0);
	}

	@Test
	public void testCalculateSystemPower() {
		double power = pvGridConnectedSystemPowerSizing.matchSystemPower();
		assertEquals(3.05,power,0.1);
		System.out.println(power);

	}

	private static List<Weather> getMeteorologicalYear() throws IOException {
		String fileName = "src/test/resources/Krakow.txt";
		List<Weather> meteorologicalYear = readLines(fileName);
		return meteorologicalYear;
	}

	public static List<Weather> readLines(String filename) throws IOException {
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<Weather> pogody = new LinkedList<Weather>();
		List<String> lines = new LinkedList<String>();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			lines.add(line);
		}
		bufferedReader.close();
		for (String linia : lines) {
			linia = linia.replaceAll("\\s+", " ");
			String[] wiersz = linia.split(" ");
			if (wiersz.length == 46) {
				Weather pogoda = new Weather();
				pogoda.setIrradiance(Double.parseDouble(wiersz[26]));
				pogody.add(pogoda);
			} else {
				Weather pogoda = new Weather();
				pogoda.setIrradiance(Double.parseDouble(wiersz[27]));
				pogody.add(pogoda);
			}
			
		}
		return pogody;
	}


}
