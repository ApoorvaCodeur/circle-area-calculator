package com.example.circleareacalculator.service.impl;

import com.example.circleareacalculator.service.CircleAreaCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * This is a basic implementation of service to calculate surface area of a circle
 *
 * @author apoorvachauhan
 * @version 1.0
 */
@Service
class CircleAreaCalculatorServiceImpl implements CircleAreaCalculatorService {

    private static final Logger LOG = LoggerFactory.getLogger(CircleAreaCalculatorServiceImpl.class);

    @Override
    public double calculate(double radius) {
        double area = radius * radius * Math.PI;
        LOG.info("The area of the circle [radius = " + radius + "]: " + area);
        return area;
    }

}
