package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
class PersonServiceTest {

    @SpyBean
    PersonService personService;

    @Test
    void name() {
        PersonDto personDto = new PersonDto();
        personService.save(personDto);
    }
}