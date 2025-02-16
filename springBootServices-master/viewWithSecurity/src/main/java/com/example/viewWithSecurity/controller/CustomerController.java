package com.example.viewWithSecurity.controller;

import com.example.viewWithSecurity.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigInteger;
import java.util.*;

@Controller
public class CustomerController {

    private static final String CUSTOMER_ENDPOINT_URL = "http://localhost:8000/api/mvcMongo/mongo/users";

    @Autowired
    @Qualifier("normalRestTemplate")
    RestTemplate restTemplate;

    //call pages
    @GetMapping("/inserCustPage")
    public ModelAndView insertPage() {
        return new ModelAndView("customers/insert", "custForm", new Customer());
    }

    //call rest
    @PostMapping(params = "save")
    public String addCustomer(@ModelAttribute("custForm") Customer customer) {

        System.out.println("savvvveeee");
        restTemplate.postForEntity(CUSTOMER_ENDPOINT_URL, customer, String.class);
//        return "customers/customers";  // does not work
        //after redirect you should have action not view!!!
        return "redirect:/getAllCustomers";
    }

    @PostMapping(value = "/updateCustomer")
    public String updCustomer(Model model, @RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("family") String family) {

        BigInteger bigInteger = new BigInteger(id);
        Customer customer = new Customer(bigInteger, name, family);

        restTemplate.put(CUSTOMER_ENDPOINT_URL+"/"+id, customer);
        return "redirect:/getAllCustomers";
    }

    @PostMapping(params = "search")
    public String searchCustomer(Model model, @ModelAttribute("custForm") Customer customer) {

        System.out.printf("search!!!!!!!!!!!!!!!!!!!!!");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//        headers.setBasicAuth(username, password);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CUSTOMER_ENDPOINT_URL+"/search")
                .queryParam("id",customer.getId())
                .queryParam("name",customer.getName())
                .queryParam("family",customer.getFamily());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Customer>>() {
                });


        List<Customer> list=responseEntity.getBody();
        System.out.println("no:"+ list.size());
        model.addAttribute("list",list);
        model.addAttribute("custForm", new Customer());

        return "customers/customers";
    }


    @GetMapping(value = "/getAllCustomers")
    public ModelAndView getCustomers() {

        List<Customer> list = getAll();
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("custForm", new Customer());
        modelMap.put("list", list);

        return new ModelAndView("customers/customers", modelMap);
    }

    //call rest
    @GetMapping(value = "/deleteCustomer")
    public String updCustomer( @RequestParam("id") String id) {

//        Map<String,String> params=new HashMap<>();
//        params.put("id",id);
        restTemplate.delete(CUSTOMER_ENDPOINT_URL+"/"+id);

        return "redirect:/getAllCustomers";
    }


    private List<Customer> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//        headers.setBasicAuth(username, password);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CUSTOMER_ENDPOINT_URL);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Customer>>() {
                });

        return responseEntity.getBody();

    }
}
