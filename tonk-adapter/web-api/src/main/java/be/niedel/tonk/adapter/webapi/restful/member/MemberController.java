package be.niedel.tonk.adapter.webapi.restful.member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class MemberController {

    @GetMapping(path = "/member")
    public String hello() {
        return "Hello";
    }


}
