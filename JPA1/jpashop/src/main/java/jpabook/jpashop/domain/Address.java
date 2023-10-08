package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable//JPA의 내장 타입이기 때문에 어딘가에 내장이 될 수 있다는 annotation
@Getter //값이 변경이 되면 안된다.
//Setter를 사용하면 유지보수가 어려워진다.
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //JPA는 프록시 같은 기술을 쓸 대가 많은데 기본 생성자가 필요하다.
    //JPA 스펙상 만드는 생성자를 생성하지 못하도록 protected를 해야 안전하다.
    protected Address() {
    }

    //값을 변경할 수 없게 된다. 생성할 때만 설정한다.
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
