package com.example.circleareacalculator.controller;

import com.example.circleareacalculator.service.CircleAreaCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is the main controller providing REST API endpoint for Circle Area Calculator
 *
 * @author apoorvachauhan
 * @version 1.0
 */
@RestController()
public class CircleAreaCalculatorController {

    private static final Logger LOG = LoggerFactory.getLogger(CircleAreaCalculatorController.class);
    private static final String INVALID_RESPONSE_MESSAGE = "Radius is invalid. It should be a positive number";
    private static final String ERROR_RESPONSE_MESSAGE = "Internal Error. Please contact support.";

    private final CircleAreaCalculatorService circleAreaCalculatorService;

    /**
     * Constructor that takes service as input
     *
     * @param circleAreaCalculatorService
     */
    @Autowired
    CircleAreaCalculatorController(CircleAreaCalculatorService circleAreaCalculatorService) {
        this.circleAreaCalculatorService = circleAreaCalculatorService;
    }

    /**
     * GET HTTP method to take radius of circle as input in path variable and return it's area
     * Use - /circle/area/{radius}
     *
     * @param radius of the circle which should be a numeric value greater than zero
     */
    @GetMapping("/circle/area/{radius}")
    public ResponseEntity calculateArea(@PathVariable final double radius) {
        LOG.info("Request received :: radius of the circle = " + radius);

        if (radius <= 0) {
            // If radius is invalid less than or equal to zero, return bad request
            LOG.warn(INVALID_RESPONSE_MESSAGE);
            return new ResponseEntity<>(INVALID_RESPONSE_MESSAGE, HttpStatus.BAD_REQUEST);
        } else {
            // If radius is valid, calculate area and return success response
            return new ResponseEntity<>(circleAreaCalculatorService.calculate(radius), HttpStatus.OK);
        }
    }

    /**
     * This is a generic exception handler to handle internal server errors and send appropriate response status and message
     *
     * @param exception
     * @return
     */
    @ExceptionHandler
    public ResponseEntity handleException(final Exception exception) {
        LOG.error(String.valueOf(exception));
        return new ResponseEntity<>(ERROR_RESPONSE_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
