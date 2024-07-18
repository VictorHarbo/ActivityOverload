package dk.harbojohnston.activityoverload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/weight")
public class WeightController {
    @Autowired
    private WeightService weightService;

    @GetMapping
    public List<Weight> getAllWeights() {
        return weightService.getAllWeights();
    }

    @PostMapping
    public Weight createWeight(@RequestBody Weight weight){
        return weightService.saveWeight(weight);
    }

    @GetMapping("/add")
    public Weight addWeight(@RequestParam("value") Double value, @RequestParam(value = "date", required = false) String date) {
        Weight weight = new Weight();
        weight.setWeightInKilograms(value);
        weight.setDate(Objects.requireNonNullElseGet(date, () -> LocalDate.now().format(DateTimeFormatter.ISO_DATE)));
        return weightService.saveWeight(weight);
    }
}


