package yoon.test.aopTest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yoon.test.aopTest.request.LoginDto;
import yoon.test.aopTest.request.MemberDto;
import yoon.test.aopTest.response.MemberResponse;
import yoon.test.aopTest.response.Message;
import yoon.test.aopTest.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MainController {

    private final MemberService memberService;

    @GetMapping("/")
    public ResponseEntity<Message> home(){
        Message message = new Message();

        message.setStatus(HttpStatus.OK);
        message.setMessage("Main Page");
        message.setData("Home");

        return ResponseEntity.ok(message);
    }

    @PostMapping("/")
    public ResponseEntity<Message> login(@RequestBody @Validated MemberDto dto){
        Message message = new Message();
        MemberResponse result = memberService.join(dto);

        message.setStatus(HttpStatus.OK);
        message.setMessage("Register Success");
        message.setData(result);

        return ResponseEntity.ok(message);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginDto dto){
        Message message = new Message();
        MemberResponse result = memberService.login(dto);

        message.setStatus(HttpStatus.OK);
        message.setMessage("Login Success");
        message.setData(result);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/user")
    public ResponseEntity<?> userPage(){

        return null;
    }

    @GetMapping("/admin")
    public ResponseEntity<?> adminPage(){

        return null;
    }
}
