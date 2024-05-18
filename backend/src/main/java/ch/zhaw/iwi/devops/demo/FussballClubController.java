package ch.zhaw.iwi.devops.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class FussballClubController {

    private Map<Integer, FussballClub> fussballClubs = new HashMap<Integer, FussballClub>();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        this.fussballClubs.put(1, new FussballClub(1, "Real Madrid", "14 Tiel"));
        this.fussballClubs.put(2, new FussballClub(2, "Manchester City", "1 Titel"));
        this.fussballClubs.put(3, new FussballClub(3, "Arsenal FC", "3 Titel"));
        this.fussballClubs.put(4, new FussballClub(4, "FC Aarau", "5 Titel"));
        this.fussballClubs.put(5, new FussballClub(5, "FC St. Gallen", "0 Titel"));
        System.out.println("Init Data");
    }

    @GetMapping("/test")
    public String test() {
        return "Fussball Club app is up and running!";
    }

    @GetMapping("/services/ping")
    public String ping() {
        String languageCode = "de";
        return "{ \"status\": \"ok\", \"userId\": \"admin" + "\", \"languageCode\": \"" + languageCode + "\", \"version\": \"0.0.1" + "\"}";
    }

    @GetMapping("/count")
    public int count() {
        return this.fussballClubs.size();
    }

    @GetMapping("/services/fc")
    public List<PathListEntry<Integer>> fc() {
        var result = new ArrayList<PathListEntry<Integer>>();
        for (var fc : this.fussballClubs.values()) {
            var entry = new PathListEntry<Integer>();
            entry.setKey(fc.getId(), "fcKey");
            entry.setName(fc.getTitle());
            entry.getDetails().add(fc.getDescription());
            entry.setTooltip(fc.getDescription());
            result.add(entry);
        }
        return result;
    }

    @GetMapping("/services/fc/{key}")
    public FussballClub getFc(@PathVariable Integer key) {
        return this.fussballClubs.get(key);
    }

    @PostMapping("/services/fc")
    public void createFc(@RequestBody FussballClub fc) {
        var newId = this.fussballClubs.keySet().stream().max(Comparator.naturalOrder()).orElse(0) + 1;
        fc.setId(newId);
        this.fussballClubs.put(newId, fc);
    }

    @PutMapping("/services/fc/{id}")
    public void updateFc(@PathVariable Integer id, @RequestBody FussballClub fc) {
        fc.setId(id);
        this.fussballClubs.put(id, fc);
    }

    @DeleteMapping("/services/fc/{key}")
    public FussballClub deleteFc(@PathVariable Integer key) {
        return this.fussballClubs.remove(key); } }
