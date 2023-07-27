package yoon.test.aopTest.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MemberResponse {

    private String email;

    private String name;

    private String role;

    private LocalDateTime regdate;

}
