package com.ovidonius;

import com.ovidonius.graph.CityGraph;
import com.ovidonius.models.Offer;
import com.ovidonius.services.OfferGenerator;

import java.util.ArrayList;
import java.util.List;

public class TestGenerator {
    public static void main(String[] args) {
        CityGraph.init();
        List<Offer> offers = OfferGenerator.generateOffers(1);
    }
}
