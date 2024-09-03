/********************************************************************************
 * This class represents a Report object that contains information related to
 * risk estimation. It utilizes Lombok annotations for getter and setter methods.
 ********************************************************************************/
package com.thery.report.model;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Report {

    /**
     * The risk estimation associated with the report.
     */
    String riskEstimation;

    /**
     * Default constructor for the Report class.
     * This constructor is provided by Lombok.
     */
    public Report() {
        //lombok constructor
    }
}