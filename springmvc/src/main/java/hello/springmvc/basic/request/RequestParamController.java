package hello.springmvc.basic.request;


import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;


@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {} , age = {}", username, age);

        response.getWriter().write("ok");
    }

    //Controller면서 String이면 View를 찾게 됨으로 ResponseBody로 반환한다.
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam ("username")String memberName,
            @RequestParam ("age") int memberAge)
            {
                log.info("username = {} , age = {}", memberName, memberAge);
                return "ok";
            }
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age)
    {
        log.info("username = {} , age = {}", username, age);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username = {} , age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            //기본값이 true기 때문에 인텔리제이에서 자체 검열, null은 통과 X, ""는 빈문자열로 통과
            @RequestParam(required= true) String username,
            //integer는 null값이 들어갈 수 있다. int -> integer
            @RequestParam(required = false)  Integer age) {
        log.info("username = {} , age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            //defaultValue는 빈 문자의 경우에도 설정한 기본값이 적용된다. username=
            @RequestParam(required= true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1")  int age) {
        log.info("username = {} , age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username = {} , age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username = {} , age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    //스프링은 String, int, Integer같은 단순 타입 -> @RequestParam
    // 나머지는 @ModelAttribute (argument resolver로 지정해둔 타입)
    public String modelAttributeV2(HelloData helloData){
        log.info("username = {} , age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

}
