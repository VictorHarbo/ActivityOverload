package dk.harbojohnston.activityoverload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/weight")
public class WeightController {
    private static final Logger log = LoggerFactory.getLogger(WeightController.class);

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
        log.info("Adding entry to weights table with value: '{}'", value);

        Weight weight = new Weight();
        weight.setWeightInKilograms(value);
        weight.setDate(Objects.requireNonNullElseGet(date, () -> LocalDate.now().format(DateTimeFormatter.ISO_DATE)));
        return weightService.saveWeight(weight);
    }

    @GetMapping("/min")
    public double getMinWeight(){
        return weightService.getMinWeight();
    }

    @GetMapping("/delete")
    public String deleteByID(@RequestParam("id") Long id){
        int result = weightService.deleteWeightEntry(id);

        if (result != 0){
            String mes = "Error happened while trying to delete record with ID: '"+ id + "'.";
            log.warn(mes);
            return mes;
        }

        log.info("Deleted record with id: '{}'", id);
        return "Record with ID '" + id + "' successfully deleted";


    }
}


