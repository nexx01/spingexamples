package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Service
@Validated
public class PersonService {

    public void save(@Valid PersonDto personDto) {
        // do something
    }

    public void custom(@CustomValidateAnnotation DTOCustomValidate dtoCustomValidate) {

    }

}
