package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    // url에 /hello가 들어오면 이 메소드를 호출해줌
    @GetMapping("hello")

    //그러면 스프링이 모델을 만들어서 넣어준다!
    public String hello(Model model) {

        //값을 넣고
        model.addAttribute("data", "헬로!!!");

        //여기의 hello 는 리소스의 템플릿의 hello.html 을 찾아서 걔한테 가서 그 화면을 실행시켜라
        //데이터는 모델을 화면에 넘기면서!
        //스프링부트 왈 : hello 가 리턴되었다? 리소스-템플릿 하위에서 hello.html을 찾아서 렌더링한다.
        //문자를 리턴하면 뷰 리졸버가 화면을 찾아서 처리한다.
        //그럼 모델이랑 같이 넘기나 보지? 그래서 data 를 출력할 수 있는 것!
        return "hello";
    }
}

//빌드하고 실행하기 : .jar 파일이 있는데 걔만 서버에 넣고 실행시키면 서버가 돌아가는 걸 볼 수 있다.
