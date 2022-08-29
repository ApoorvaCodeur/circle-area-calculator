package com.example.circleareacalculator.service;

import org.springframework.stereotype.Service;

/**
 * This is a service to calculate surface area of a circle when the radius is provided
 *
 * @author apoorvachauhan
 * @version 1.0
 */
@Service
public interface CircleAreaCalculatorService {

    /**
     * Calculate Surface Area of a circle with provided radius
     *
     * @param radius
     * @return area
     */
    double calculate(double radius);
}
