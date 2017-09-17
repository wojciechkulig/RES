package org.kulig.renewableenergy.economicAnalysis;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class IRRCalculator {
	/*
	 * newton rapson
	 * Xn+1 = Xn - f(xn)/f'(xn)
	 */
	public double getIRR(List<Double> cashFlows) {
		int maxIterationCount = 20;
        double absoluteAccuracy = 1E-7;

        double x0 = 0.01;
        double x1;

        int i = 0;
        while (i < maxIterationCount) {

            // the value of the function (NPV) and its derivate can be calculated in the same loop
            double fValue = 0;
            double fDerivative = 0;
            for (int k = 0; k < cashFlows.size(); k++) {
                fValue += cashFlows.get(k) / Math.pow(1.0 + x0, k);
                fDerivative += -k * cashFlows.get(k) / Math.pow(1.0 + x0, k + 1);
            }

            // the essense of the Newton-Raphson Method
            x1 = x0 - fValue/fDerivative;

            if (Math.abs(x1 - x0) <= absoluteAccuracy) {
                return round(x1*100,2);
            }
            x0 = x1;
            ++i;
        }
        // maximum number of iterations is exceeded
        return Double.NaN;
    }
	private double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
