package com.example.scheduler.service;

import com.example.scheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransService {
    private final UserRepository userRepository;
    private final UserService userService;


    @Transactional
    public void addUser(String name){
        userService.addUser(name);
        userService.addUserByMyBatis(name);
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void addUser(Integer i){
//        User insertItem = new User().builder()
//                .name(Integer.toString(i))
//                .build();
//
//        testRepository.save(insertItem);
//        if(1==1) throw new RuntimeException("AAA");
//        User item = testRepository.getByIdx(insertItem.getIdx());
//    }
//
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void addNewUser(Integer i){
//        User insertItem = new User().builder()
//                .name(Integer.toString(i))
//                .build();
//
//        testRepository.save(insertItem);
////        if(1==1) throw new RuntimeException("AAA");
//        User item = testRepository.getByIdx(insertItem.getIdx());
//    }
}
