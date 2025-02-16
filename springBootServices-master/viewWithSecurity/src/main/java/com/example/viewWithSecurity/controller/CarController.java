package com.example.viewWithSecurity.controller;


import com.example.viewWithSecurity.entity.Car;
import com.example.viewWithSecurity.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Controller
public class CarController {

    //this endpoint returns hypertext, so we needs do some extra things to consume data.
    // it needs a dependency (that i have in pom.xml)
    //this class is not finished
    private static final String CAR_ENDPOINT_URL="http://localhost:8000/api/restRepo/cars";

    @Autowired
    @Qualifier("hyperTextRestTemplate")
    private RestTemplate restTemplate;

    @RequestMapping("/insert-car-page")
    public ModelAndView insPage() {
        return new ModelAndView("cars/insert", "carForm", new Car());
    }

    @RequestMapping(value = "/getCars")
    @ResponseBody
    public Object gerCars() {

        ResponseEntity re;
        re = restTemplate.getForEntity(CAR_ENDPOINT_URL, String.class);

        return re.getBody();
    }

    @RequestMapping(value = "/getCar/{id}")
    @ResponseBody
    public Object gerCar(@PathVariable String id) {

        ResponseEntity re;
        re = restTemplate.getForEntity(CAR_ENDPOINT_URL + id, String.class);

        return re.getBody();
    }

    @RequestMapping(value = "/updateCar/{id}")//update
    @ResponseBody
    public Object updateCar(@PathVariable String id, @RequestBody Car car) {

        ResponseEntity re;

//        re = restTemplate.exchange("http://localhost:5050/cars/"+id, HttpMethod.PUT, car,String.class);
        restTemplate.put("http://localhost:8000/restRepo/cars/" + id, car, String.class);
        return "success";
    }

    @RequestMapping(value = "/delCar/{id}")//delete
    @ResponseBody
    public Object deleteCar(@PathVariable String id) {

        ResponseEntity re;

//        re = restTemplate.exchange("http://localhost:5050/cars/"+id, HttpMethod.PUT, car,String.class);
        restTemplate.delete("http://localhost:5050/cars/" + id);
        return "success";
    }

//    @PostMapping(value = "/save-car")//create
//    @ResponseBody
//    public Object setCar(@ModelAttribute("carForm") Car car) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
////        headers.setBasicAuth(username, password);
//
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:5050/cars/");
//        HttpEntity<?> entity = new HttpEntity<>(headers);
// String res = restTemplate.exchange(
//                "http://localhost:8000/restRepo/cars/",
//                HttpMethod.POST,
//                car,
//                String.class);
//
//        return responseEntity.getBody();
//   }

}
