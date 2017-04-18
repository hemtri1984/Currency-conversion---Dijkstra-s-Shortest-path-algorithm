package hemant.com.currencyconverter.utils;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hemant.com.currencyconverter.models.RateConversionModel;

/**
 * Using shortest path algorithm to find the minimum number of
 * conversions from some currency to GBP
 * <p>
 * Created by Hemant on 4/11/17.
 */

public class RateConversionShortestPath {

    private static final String GBP_SIGN = "GBP";

    private static Map<String, Double> currencyRateMap = new HashMap<>();

    public static double getConversionValue(List<RateConversionModel> rateConversionModelList, String fromCurrency) {
        CurrencyConverter currencyConverter = new CurrencyConverter();
        boolean isConversionAvailable = false;
        for(RateConversionModel rateConversionModel:rateConversionModelList) {
            if(rateConversionModel.getTo().equals(GBP_SIGN)) {
                currencyRateMap.put(rateConversionModel.getFrom(), Double.parseDouble(rateConversionModel.getRate()));
            }
            if(rateConversionModel.getTo().equals(fromCurrency)) {
                isConversionAvailable = true;
            }
            if(rateConversionModel.getFrom().equals(GBP_SIGN)) {
                currencyRateMap.put(rateConversionModel.getTo(), 1.0/Double.parseDouble(rateConversionModel.getRate()));
            }
            if(rateConversionModel.getFrom().equals(fromCurrency)) {
                isConversionAvailable = true;
            }
            currencyConverter.setExchangeRate(rateConversionModel.getFrom(), rateConversionModel.getTo(), Double.parseDouble(rateConversionModel.getRate()));
        }

        if(isConversionAvailable) {
            double reqRate;
            try {
                if (currencyRateMap.containsKey(fromCurrency)) {
                    reqRate = currencyRateMap.get(fromCurrency);
                } else {
                    reqRate = currencyConverter.convertCurrency(fromCurrency, GBP_SIGN, 1);
                }
                return reqRate;
            }catch (ArithmeticException err) {
                return 0.;
            }
        }else {
            return 0.;
        }
    }
}

class Vertex {
    public double conversionRate;
    public String edgeName;

    public Vertex(double conversionRate, String edgeName) {
        this.conversionRate = conversionRate;
        this.edgeName = edgeName;
    }
}

class CurrencyConverter {
    private SimpleDirectedGraph<String, Vertex> conversionGraph;

    public CurrencyConverter() {
        conversionGraph = new SimpleDirectedGraph<String, Vertex>(Vertex.class);
    }

    public boolean setExchangeRate(String fromCurrency, String toCurrency, double rate) {
        //add the vertexes if they do not exist
        conversionGraph.addVertex(fromCurrency);
        conversionGraph.addVertex(toCurrency);

        // check whether the edge already exists
        if (conversionGraph.containsEdge(fromCurrency, toCurrency)) {
            conversionGraph.removeEdge(fromCurrency, toCurrency);
            conversionGraph.removeEdge(toCurrency, fromCurrency);
        }
        boolean directCurrency = conversionGraph.addEdge(fromCurrency, toCurrency, new Vertex(rate, fromCurrency + toCurrency));
        boolean reverseCurrency = conversionGraph.addEdge(toCurrency, fromCurrency, new Vertex((1.0 / rate), toCurrency + fromCurrency));

        return directCurrency && reverseCurrency;
    }

    public double convertCurrency(String origin, String goal, double amount) throws ArithmeticException {
        // find the shortest path between the two currencies

        List<Vertex> l = DijkstraShortestPath.findPathBetween(conversionGraph, origin, goal);

        // when there is no path between the 2 nodes / vertices / currencies
        // DijkstraShortestPath returns null
        if (l == null)
            throw new ArithmeticException("This exchange does not exist" + origin + " to " + goal);

        // navigate the edges and iteratively compute the exchange rate
        double rate = 1.0;
        for (Vertex edge : l) {
            rate = rate * edge.conversionRate;
        }

        // compute and return the currency value
        return amount * rate;
    }
}