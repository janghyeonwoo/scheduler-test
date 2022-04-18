package com.example.scheduler.controller;

import com.example.scheduler.OrderDto;
import com.example.scheduler.QOrderDto;
import com.example.scheduler.domain.*;
import com.example.scheduler.dto.QResponseDto;
import com.example.scheduler.dto.ResponseDto;
import com.example.scheduler.repository.DoubleRepo;
import com.example.scheduler.repository.OrderRepository;
import com.example.scheduler.repository.TeamRepository;
import com.example.scheduler.repository.UserRepository;
import com.example.scheduler.service.UserService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryModifiers;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.scheduler.domain.QOrder.order;
import static com.example.scheduler.domain.QTeam.team;
import static com.example.scheduler.domain.QUser.user;



@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = { "spring.config.location=classpath:application.yml" })

class UserContollerTest {
    public static final Logger LOG = LoggerFactory.getLogger(UserContollerTest.class);


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DoubleRepo doubleRepo;

    @Autowired
    EntityManager em;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    @Rollback(false)
    @Transactional
    public void setData(){
        User creatUser = saveuser("pooney");
        User findUser = userRepository.findByIdx(creatUser.getIdx());
        saveOrder(saveuser("pooney"));
        saveOrder(findUser);
        em.flush();
        em.clear();
    }

//    @Test
//    void 조회테스트() throws Exception {
//        HttpServletResponse response = mockMvc.perform(get("/v1/mybatis"))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse();
//        String returnValue = ((MockHttpServletResponse) response).getContentAsString();
//        assertEquals("하모니",returnValue);
//    }

    @Test
    @Rollback(false)
    @Transactional
    void 회원추가테스트() throws Exception {
        saveuser("ponney");
        List<User> userList = em.createQuery("select u from User as u ", User.class).getResultList();
        System.out.println("=====================================================");
        for(User user : userList){
            System.out.println("[user] : " + user);
            System.out.println("[user] : " + user);
        }
    }

    @Test
    @Rollback(false)
    @Transactional
    void QueryDslTest(){
        QueryModifiers queryModifiers = new QueryModifiers(20L, 10L);
        JPAQuery<User> query = new JPAQuery<>(em);
//        QTeam qTeam = new QTeam("t");
//        QUser qUser = new QUser("u");
        List<User> members =
                query.from(user)
                        .join(user.team,team)
                        .fetchJoin()
                        .where(
                                ((user.name.eq("pooney").or(user.team.teamName.eq("스마트스코어")))).and
                                         (user.name.eq("pooney2").or(user.team.teamName.eq("스마트스코어2"))))
                        .orderBy(user.idx.desc())
                        .groupBy(user.idx)
                        .having(user.idx.gt(10))
                        .restrict(queryModifiers)
                        .fetch();
        System.out.println("============ query dsl test ==================");
        System.out.println(members);

    }


    @Test
    @Rollback(false)
    @Transactional
    void QueryDslJoinTest(){
        JPAQuery<Order> query = new JPAQuery<>(em);
        QUser user2 = new QUser("s");
        List<OrderDto> orders =
                query.select(new QOrderDto(
                        order.user.name,
                        order.price
                        ))
                        .from(order)
                        .innerJoin(order.user,user)
//                        .fetchJoin()
                        .where(order.user.name.eq(
                        JPAExpressions
                                .select(user2.name)
                                .from(user2)
                                .where(user2.idx.eq(2L)))
                                .or(whereDynamicQuery())
                ).fetch();

        System.out.println();

        System.out.println("============ query dsl test ==================");
        System.out.println(orders);
    }

    @Test
    @Rollback(false)
    @Transactional
    void Projection_Test(){
        QUser qUser = new QUser("q");

        List<ResponseDto> resultList = jpaQueryFactory
                .select(new QResponseDto(
                                qUser.name,
                                qUser.idx))
                .from(qUser)
                .fetch();
        System.out.println("=========== Projection test ===============");
        System.out.println(resultList);
        System.out.println("=========== Projection test ===============");
    }



    @Test
    @Rollback(false)
    @Transactional
    void JPA레파지토리_상속구조_테스트(){
        System.out.println("==========JPA레파지토리_상속구조_테스트================= ");
        System.out.println(orderRepository.findAll().size() );
        System.out.println("==========JPA레파지토리_상속구조_테스트================= ");
    }



    @Test
    @Rollback(false)
    @Transactional
    void COALESCE_NULLIF_TEST(){
        List<String> resultList = em.createQuery("SELECT nullif(o.address.street,'의정부2') from orders as o", String.class).getResultList();
        System.out.println(resultList.get(0));
    }

    BooleanBuilder whereDynamicQuery(){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(order.user.name.eq("하하하"));
        booleanBuilder.and(order.price.gt(500));
        return booleanBuilder;
    }


    public User saveuser(String userName){
        Team team = saveTeam();
        User user = new User()
                .builder()
                .name(userName)
                .team(team)
                .build();
        userRepository.save(user);
        return user;
    }

    public Order saveOrder(User user){
        Address address = this.saveOrderAddress();
        Order order = new Order()
                .builder()
                .address(address)
                .ordNo("20201919292")
                .price(1000)
                .user(user)
                .build();

        orderRepository.save(order);
        return order;
    }

    public Address saveOrderAddress(){
        Address address = new Address()
                .builder()
                .street("의정부")
                .zipCode(10003)
                .build();
        return address;
    }

    public Team saveTeam(){
        Team team = new Team()
                .builder()
                .teamName("스마트스코어")
                .build();
        teamRepository.save(team);
        return team;
    }
}

