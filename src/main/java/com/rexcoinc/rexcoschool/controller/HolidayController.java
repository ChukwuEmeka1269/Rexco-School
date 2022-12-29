package com.rexcoinc.rexcoschool.controller;

import com.rexcoinc.rexcoschool.model.Holiday;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayController {

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
        List<Holiday> holidays = Arrays.asList(
                new Holiday("Jan 1", "New Year's day", Holiday.Type.FESTIVAL),
                new Holiday("Oct 31", "Halloween", Holiday.Type.FESTIVAL),
                new Holiday("Nov 24", "Thanksgiving day", Holiday.Type.FESTIVAL),
                new Holiday("Dec 25", "Christmas day", Holiday.Type.FESTIVAL),
                new Holiday("Jan 17", "Martin Luther King jr. day", Holiday.Type.FEDERAL),
                new Holiday("July 4", "Independence day", Holiday.Type.FEDERAL),
                new Holiday("Sept 5", "Labor day", Holiday.Type.FEDERAL),
                new Holiday("Nov 11", "Veterans day", Holiday.Type.FEDERAL)
        );

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
