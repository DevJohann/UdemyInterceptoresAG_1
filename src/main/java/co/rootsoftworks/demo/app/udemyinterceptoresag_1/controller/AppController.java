package co.rootsoftworks.demo.app.udemyinterceptoresag_1.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {
    
    @GetMapping("/foo")
    public Map<String, String> foo(){
        return Collections.singletonMap("message", "Handler foo del controlador");
    }

    @GetMapping("/var")
    public Map<String, String> var(){
        return Collections.singletonMap("message", "Handler var del controlador");
    }

    @GetMapping("/buz")
    public Map<String, String> buz(){
        return Collections.singletonMap("message", "Handler buz del controlador");
    }
}
