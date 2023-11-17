package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Item.Book;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    EntityManager em;
    @Autowired OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{


        //given
        Member member = createMember();
        Book book = createBook("jpa",10000,10);

        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);


        //then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("상품주문시 상태는 ORDER",
                OrderStatus.ORDER,
                getOrder.getStatus());
        Assert.assertEquals("주문한 상품 종류 수가 정확해야함",getOrder.getOrderItems().size());
        Assert.assertEquals("주문 가격은 가격 * 수량이다.",10000 * orderCount, getOrder.getTotalPrice());
        Assert.assertEquals("주문 수량만큼 재고가 줄어야 한다.",8, book.getStockQuantity());

    }
    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{

        //given
        Member member = createMember();
        Book item = createBook("jpa",10000,10);

        int orderCount = 11;

        //when
        orderService.order(member.getId(),item.getId(),orderCount);

        //then
        fail("재고 수량 부족");

    }
    @Test(expected = NotEnoughStockException.class)
    public void 주문취소() throws Exception{
        //given
        Member member = createMember();
        Book book = createBook("jpa",10000,10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //when

        orderService.cancelOrder(orderId);


        //then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("주문 취소시 상태는 CANCEL 이다.", OrderStatus.CANCEL, getOrder.getStatus());
    }



    private Book createBook(String jpa, int price, int stockQuantity){
        Book book = new Book();
        book.setName(jpa);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember(){
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "길","123"));
        em.persist(member);
        return member;
    }
}
