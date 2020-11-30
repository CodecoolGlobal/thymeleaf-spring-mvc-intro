package com.raczkowski.springintro;

import com.raczkowski.springintro.backdoor.entity.Customer;
import com.raczkowski.springintro.backdoor.entity.Order;
import com.raczkowski.springintro.backdoor.repository.CustomerRepository;
import com.raczkowski.springintro.github.configuration.ApplicationProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
public class Application {

    private CustomerRepository customerRepository;
    private ApplicationProperties applicationProperties;

    public Application(CustomerRepository customerRepository,
                       ApplicationProperties applicationProperties) {
        this.customerRepository = customerRepository;
        this.applicationProperties = applicationProperties;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return (args) -> {
            customerRepository.saveAll(createCustomers());

            System.out.println(applicationProperties.getGithub().getUsername());
        };
    }

    private List<Customer> createCustomers() {
        return asList(
                new Customer(
                        "Tomasz Dąbrowski",
                        "Krakow, ul. Kazimierza Wielkiego 134/2",
                        new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(),
                        asList(
                                new Order(
                                        "TV",
                                        3452
                                ),
                                new Order(
                                        "PC",
                                        4500
                                ))),
                new Customer(
                        "Franciszek Pajda",
                        "Krakow, ul. Jana Pawła 256",
                        new GregorianCalendar(2018, Calendar.JANUARY, 11).getTime(),
                        asList(
                                new Order(
                                        "Iphone",
                                        5500
                                ),
                                new Order(
                                        "Xiami",
                                        2762
                                ))),
                new Customer(
                        "Eugeniusz Jaremek",
                        "Krakow, ul. Szewska 55/23",
                        new GregorianCalendar(1998, Calendar.DECEMBER, 11).getTime(),
                        asList(
                                new Order(
                                        "Iphone",
                                        5500
                                ),
                                new Order(
                                        "Xiami",
                                        2762
                                ))),
                new Customer(
                        "Mare Franco",
                        "Krakow, ul. Galicyjska 202/1",
                        new GregorianCalendar(2012, Calendar.OCTOBER, 11).getTime(),
                        asList(
                                new Order(
                                        "Iphone",
                                        5500
                                ),
                                new Order(
                                        "Xiami",
                                        2762
                                ))),
                new Customer(
                        "Tomasz Bąbel",
                        "Krakow, ul. Św. Tomasza 10",
                        new GregorianCalendar(2020, Calendar.JANUARY, 20).getTime(),
                        asList(
                                new Order(
                                        "Iphone",
                                        5500
                                ),
                                new Order(
                                        "Xiami",
                                        2762
                                ))));
    }

}
