package com.springboot.springbatchprocessor.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Sales {
    @Id
    private Long id;
    private String transactionDate;
    private String product;
    private String price;
    private String paymentType;
    private String name;
    private String city;
    private String state;
    private String country;
    private String accountCreated;
    private String lastLogin;
    private String latitude;
    private String longitude;
    private String zip;
    private Date lastUpdatedDate;

}
