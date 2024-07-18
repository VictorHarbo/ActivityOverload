package dk.harbojohnston.activityoverload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class WeightService {
    @Autowired
    private WeightRepository weightRepository;

    public List<Weight> getAllWeights() {
        return weightRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }

    public Weight saveWeight(Weight weight) {
        return weightRepository.save(weight);
    }

    public double getMinWeight() {
        return weightRepository.getMinWeight();
    }
}
