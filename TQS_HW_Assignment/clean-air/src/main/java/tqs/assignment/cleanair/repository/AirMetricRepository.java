package tqs.assignment.cleanair.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.assignment.cleanair.model.AirMetric;

/**
 * AirMetricRepository
 */
@Repository
public interface AirMetricRepository extends JpaRepository< AirMetric,Long>{

    
}