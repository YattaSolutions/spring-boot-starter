package backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController
{
   @RequestMapping("/hello")
   @ResponseBody
   String hello ()
   {
      return "Hello, World!";
   }

   @RequestMapping("/greet")
   @ResponseBody
   String greet (@RequestParam (name = "name") String name)
   {
      return "Hello," + name + "!";
   }
}
