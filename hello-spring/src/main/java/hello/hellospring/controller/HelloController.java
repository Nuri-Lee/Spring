package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {  // 정적 컨텐츠 : 파일을 그대로 내려준다.
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")    // 템플릿 엔진을 MVC 방식으로 쪼개서 뷰를 템플릿 엔진으로 렌더링이 된 HTML을 Client에게 전달해준다.
    public String helloMvc (@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   // HTTP Body 부문에 데이터를 직접 넣겠다는 의미 -> 페이지 소스보기 하면 HTML 태그 없이 데이터만 있음
    public String helloString (@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")    // API는 객체를 반환하는 것이다. HttpMessageConverter을 통해서 JSON 스타일로 반환해준다. ( View 없이 반환 )
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   //객체로 반환하면 JSON 형식으로 나타냄.
    }

    static class Hello {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
