package com.example.scheduler.controller;

import com.example.scheduler.annotation.CustomPerson;
import com.example.scheduler.annotation.Person;
import com.example.scheduler.service.TransService;
import com.example.scheduler.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequiredArgsConstructor
public class UserContoller {
    private final TransService transService;
    private final UserService userService;


    @GetMapping("/v1/add")
    public ResponseEntity<?> addUser(@RequestParam("name") String name){
        transService.addUser(name);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/v1/user")
    public ResponseEntity<?> getUser(@RequestParam("idx") Long idx){
        return new ResponseEntity<>(userService.findUser(idx), HttpStatus.OK);
    }

    @GetMapping("/v1/mybatis")
    public ResponseEntity<?> getTest(){
        String name = userService.getUserByMyBatis();
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @PostMapping("/v1/args")
    public ResponseEntity<?> getArgs(HttpServletRequest request,  @CustomPerson Person person){
        System.out.println("-----========="+person);
        return new ResponseEntity<>(person,HttpStatus.OK);
    }
}
