package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable//JPA의 내장 타입이기 때문에 어딘가에 내장이 될 수 있다는 annotation
@Getter @Setter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
