package com.example.scheduler.service;

import com.example.scheduler.domain.User;
import com.example.scheduler.repository.TestRepo;
import com.example.scheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TestRepo testRepo;

    public void addUser(String name){
        User insertItem = new User().builder()
                .name(name)
                .build();

        userRepository.save(insertItem);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addNewUser(Integer i){
        User insertItem = new User().builder()
                .name("JPA")
                .build();

        userRepository.save(insertItem);
//        if(1==1) throw new RuntimeException("AAA");
//        User item = userRepository.getByIdx(insertItem.getIdx());
    }

    public User findUser(Long idx){
        return userRepository.findByIdx(idx);
    }

    public List<User> findUser(String name){
        return userRepository.findByName(name);
    }

    public String getUserByMyBatis(){
        List<User> list  = testRepo.findALl();
        log.info("[findUser] : "+ list.get(0));
        return list.get(0).getName();
    }

    public void addUserByMyBatis(String name){
        testRepo.insertUser("name");
//        if(1==1) throw new RuntimeException("AAA");
    }
}
