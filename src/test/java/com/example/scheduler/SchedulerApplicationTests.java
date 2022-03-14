package com.example.scheduler;

import com.example.scheduler.domain.Team;
import com.example.scheduler.domain.User;
import com.example.scheduler.exception.CommException;
import com.example.scheduler.repository.TeamRepository;
import com.example.scheduler.repository.UserRepository;
import com.example.scheduler.test.TestClass;
import com.example.scheduler.test.TestFunInterface;
import com.example.scheduler.type.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.log.Log;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntBiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = { "spring.config.location=classpath:application.yml" })
class SchedulerApplicationTests {
    public static final Logger LOG = LoggerFactory.getLogger(SchedulerApplicationTests.class);

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Test
    void contextLoads() {

    }

    @Test
    void 중첩EXCEPTION_Test(){
        IOException ioException = new IOException("IOE 예외발생");
        throw new CommException("이것도 에러발생",ioException);
    }

    @Test
    void ENUM비교(){
        assertEquals(true,Member.MEMBER.equals(Member.MEMBER));
    }

    @Test
    void ObjectUtils비교(){
        LOG.info("================ OBJECT NULL ============");
        Object obj = null;
        LOG.info(""+ObjectUtils.isEmpty(obj));
        LOG.info("================ OBJECT 빈값 ============");
        obj = "";
        LOG.info(""+ObjectUtils.isEmpty(obj));
        LOG.info("================ OBJECT 빈배열 ============");
        obj = new String[]{};
        LOG.info(""+ObjectUtils.isEmpty(obj));
        LOG.info("================ OBJECT 인스턴스 생성 ============");
        obj = new Object();
        LOG.info(""+ObjectUtils.isEmpty(obj));
        LOG.info("================ OBJECT 문자열============");
        obj = "abc";
        LOG.info(""+ObjectUtils.isEmpty(obj));
        LOG.info("================ OBJECT 숫자============");
        obj = 123;
        LOG.info(""+ObjectUtils.isEmpty(obj));
        LOG.info("================ OBJECT 빈리스트============");
        obj = new ArrayList<>();
        LOG.info(""+ObjectUtils.isEmpty(obj));
        LOG.info("================ OBJECT 리스트============");
        obj = new ArrayList<>().add(1);
        LOG.info(""+ObjectUtils.isEmpty(obj));

    }

    @Test
    @Rollback(false)
    void JPQL의_페치조인과_일반조인_차이점(){
        LOG.info("==================================================== nomal join");
        entityManager.createQuery("select u from User as u join u.team", User.class);
        LOG.info("==================================================== fetch join");
        entityManager.createQuery("select u from User as u join fetch u.team", User.class);
    }

    @Test
    @Rollback(false)
    void NEW없이_BUILDER_생성(){
        teamRepository.save(
                Team.builder()
                .teamName("userName")
                .build()
        );
    }
    @Test
    @Rollback(false)
    void 람다(){
        TestFunInterface test = (a,b) -> {
            return a+b;
        };
    }

    @Test
    void OptionalTest(){
        System.out.println("============== optional empty test ====================");
        Optional<String> optional = Optional.empty();
        System.out.println("option value : " + optional.isEmpty());
        System.out.println("option.isPresent() : " + optional.isPresent());
        System.out.println("============== optional test end ====================");

        System.out.println("============== optional of test ====================");
        Optional<String> optional1 = Optional.of("Option of");
        System.out.println("option value : " + optional1.isEmpty());
        System.out.println("option.isPresent() : " + optional1.isPresent());
        System.out.println("============== optional test end ====================");

        System.out.println("============== optional nullable test ====================");
        Optional<String> optional2 = Optional.ofNullable(null);
        System.out.println(optional2.orElse("null 이 아님"));
        optional2 = Optional.ofNullable("null이 아닙니다.");
        System.out.println(optional2.orElse(" 값이 있습니다."));

        System.out.println("============== 메소드참조 test====================");
        Optional<Integer> optional3 = Optional.ofNullable(3);
        Integer staticMaxCnt = optional3.map(TestClass::getStaticMaxCont).get();
        System.out.println("staticMaxCnt : "+staticMaxCnt);

//        IntBinaryOperator intBinaryOperator;
//        TestClass testClass = new TestClass();
//        intBinaryOperator = testClass::getMaxCont;

        Integer maxCnt = optional3.map(a-> {
            IntBinaryOperator intBinaryOperator;
            TestClass testClass = new TestClass();
            intBinaryOperator = testClass::getMaxCont;
            return intBinaryOperator.applyAsInt(a,3);
        }).get();

        ToIntBiFunction<String,String> function = String::compareTo;

        System.out.println("maxCnt : "+maxCnt);




    }
}
