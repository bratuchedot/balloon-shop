package mk.ukim.finki.balloon.shop.web.controller;

import mk.ukim.finki.balloon.shop.model.Balloon;
import mk.ukim.finki.balloon.shop.model.Manufacturer;
import mk.ukim.finki.balloon.shop.service.BalloonService;
import mk.ukim.finki.balloon.shop.service.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = {"/", "/balloons"})
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Balloon> balloons = balloonService.listAll();
        model.addAttribute("balloons", balloons);
        return "listBalloons";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam(required = false) Long id,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturer) {
        if (id != null)
            balloonService.edit(id, name, description, manufacturer);
        else
            balloonService.save(name, description, manufacturer);
        return "redirect:/balloons";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id) {
        balloonService.deleteById(id);
        return "redirect:/balloons";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model) {
        if (balloonService.findById(id).isPresent()) {
            Balloon balloon = balloonService.findById(id).get();
            List<Manufacturer> manufacturers = manufacturerService.findAll();
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("balloon", balloon);
            return "add-balloon";
        }
        return "redirect:/balloons?error=BalloonNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddBalloonPage(Model model) {
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "add-balloon";
    }

    @GetMapping("/search")
    public String searchBalloons(@RequestParam String filterBy,
                                 Model model) {
        List<Balloon> balloons;
        if (filterBy != null && !filterBy.isEmpty()) {
            model.addAttribute("filterBy", filterBy);
            balloons = balloonService.searchByNameOrManufacturersCountry(filterBy);
        } else {
            balloons = balloonService.listAll();
        }
        model.addAttribute("balloons", balloons);
        return "listBalloons";
    }

    @GetMapping("/add-man-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddManufacturerPage(Model model) {
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "add-manufacturer";
    }

    @GetMapping("/edit-man-form/{id}")
    public String getEditManufacturerPage(@PathVariable Long id, Model model) {
        if (manufacturerService.findById(id).isPresent()) {
            Manufacturer manufacturer = manufacturerService.findById(id).get();
            model.addAttribute("manufacturer", manufacturer);
            List<Manufacturer> manufacturers = manufacturerService.findAll();
            model.addAttribute("manufacturers", manufacturers);
            return "add-manufacturer";
        }
        return "redirect:/add-man-form?error=ManufacturerNotFound"; // ???
    }

    @PostMapping("/add-man")
    public String saveManufacturer(@RequestParam(required = false) Long manId,
                                   @RequestParam String manName,
                                   @RequestParam String manCountry,
                                   @RequestParam String manAddress,
                                   @RequestParam String creationDate) {
        LocalDate localDate = LocalDate.parse(creationDate);
        if (manId != null)
            manufacturerService.edit(manId, manName, manCountry, manAddress, Objects.requireNonNullElseGet(localDate, LocalDate::now));
        else
            manufacturerService.save(manName, manCountry, manAddress, Objects.requireNonNullElseGet(localDate, LocalDate::now));
        return "redirect:/balloons/add-man-form";
    }

}
