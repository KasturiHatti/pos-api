package com.anymind.pos.entities;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.anymind.pos.enums.PaymentMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Payment {
    @Id
    private String id;
    private BigDecimal originalPrice;
    private BigDecimal finalPrice;
    private Integer points;
    private PaymentMethod paymentMethod;
    private BigDecimal priceModifier;
    private ZonedDateTime createdDateTime;
    /*
     * DBP@ssw0rd
     * q5F%_Sat,i4H9e148_=M1S/Isn.xVK:8
     * 
     * docker run --detach --name=mysql --env="MYSQL_ROOT_PASSWORD=DBP@ssw0rd" --publish 6603:3306 --volume=~/Documents/root/docker/mysql/conf.d:/etc/mysql/conf.d mysql
     */
}
