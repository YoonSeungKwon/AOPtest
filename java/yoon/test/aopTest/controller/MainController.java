package yoon.test.aopTest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yoon.test.aopTest.request.LoginDto;
import yoon.test.aopTest.request.MemberDto;
import yoon.test.aopTest.response.MemberResponse;
import yoon.test.aopTest.response.ResponseMessage;
import yoon.test.aopTest.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MainController {

    private final MemberService memberService;

    @GetMapping("/")
    public ResponseEntity<ResponseMessage> home(){
        ResponseMessage responseMessage = new ResponseMessage();

        responseMessage.setStatus(HttpStatus.OK);
        responseMessage.setMessage("Main Page");
        responseMessage.setData("Home");

        return ResponseEntity.ok(responseMessage);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseMessage> login(@RequestBody @Validated MemberDto dto){
        ResponseMessage responseMessage = new ResponseMessage();
        MemberResponse result = memberService.join(dto);

        responseMessage.setStatus(HttpStatus.OK);
        responseMessage.setMessage("Register Success");
        responseMessage.setData(result);

        return ResponseEntity.ok(responseMessage);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginDto dto){
        ResponseMessage responseMessage = new ResponseMessage();
        MemberResponse result = memberService.login(dto);

        responseMessage.setStatus(HttpStatus.OK);
        responseMessage.setMessage("Login Success");
        responseMessage.setData(result);

        return ResponseEntity.ok(responseMessage);
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
