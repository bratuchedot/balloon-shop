package mk.ukim.finki.balloon.shop.web.controller;

import mk.ukim.finki.balloon.shop.model.Balloon;
import mk.ukim.finki.balloon.shop.model.Manufacturer;
import mk.ukim.finki.balloon.shop.service.BalloonService;
import mk.ukim.finki.balloon.shop.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/balloons")
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
    public String saveBalloon(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturer) {
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
    public String getAddBalloonPage(Model model) {
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "add-balloon";
    }

}
