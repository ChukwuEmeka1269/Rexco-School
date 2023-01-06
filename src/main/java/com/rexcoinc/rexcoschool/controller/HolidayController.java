package com.rexcoinc.rexcoschool.controller;

import com.rexcoinc.rexcoschool.model.Holiday;
import com.rexcoinc.rexcoschool.repository.HolidaysRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.stream.Collectors;

@Controller
public class HolidayController {

    private final HolidaysRepository holidaysRepository;

    public HolidayController(HolidaysRepository holidaysRepository) {
        this.holidaysRepository = holidaysRepository;
    }

    @RequestMapping(value = "/holidays/{display}", method = RequestMethod.GET)
    public String displayHoliday(@PathVariable String display,
                                 Model model){
        if(display != null && display.equals("all")){
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        }else if(display != null && display.equals("festival"))
            model.addAttribute("festival", true);
        else if(display != null && display.equals("federal"))
            model.addAttribute("federal", true);

        var holidays = holidaysRepository.findAllHolidays();

        Holiday.Type[] types = Holiday.Type.values();

        for(Holiday.Type type : types){
            model.addAttribute(type.toString(),
                    holidays.stream().filter(holiday -> holiday.getType().equals(type))
                            .collect(Collectors.toList())
                    );
        }

        return "holidays.html";
    }
}
