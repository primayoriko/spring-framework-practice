package my.primayoriko.springbasic.designpattern.creational.prototype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class PrototypeApplication {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Customer {
        private Integer id;
        private String name;
        private String category;
        private Integer discount;
    }

    @SpringBootApplication
    public static class Configuration {

        @Bean("standardCustomer")
        @Scope("prototype")
        public Customer standardCustomer(){
            return Customer.builder()
                    .discount(10)
                    .category("STANDARD")
                    .build();
        }

        @Bean("premiumCustomer")
        @Scope("prototype")
        public Customer premiumCustomer(){
            return Customer.builder()
                    .discount(50)
                    .category("PREMIUM")
                    .build();
        }

    }

    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(Configuration.class);

        Customer customerStd1 = context.getBean("standardCustomer", Customer.class);
        Customer customerStd2 = context.getBean("standardCustomer", Customer.class);
        Customer customerStd3 = context.getBean("standardCustomer", Customer.class);
        Customer customerPrem1 = context.getBean("premiumCustomer", Customer.class);

        System.out.println(customerStd1 == customerStd2);
        System.out.println(customerStd3 == customerStd2);
        System.out.println(customerStd1 == customerStd3);
        System.out.println(customerStd1 == customerPrem1);

        System.out.println(customerStd1);
        System.out.println(customerStd2);
        System.out.println(customerStd3);
        System.out.println(customerPrem1);

    }

}
