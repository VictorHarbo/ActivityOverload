package dk.harbojohnston.activityoverload;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {
    List<Weight> findAll(Sort sort);

    @Query("SELECT MIN(weight.weightInKilograms) FROM Weight weight")
    public double getMinWeight();
}



