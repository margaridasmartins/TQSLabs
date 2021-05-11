package tqs.assignment.cleanair.model;

import javax.persistence.Entity;  
import javax.persistence.Table; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * AirMetric
 */

@Entity 
@Table
public class AirMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
}