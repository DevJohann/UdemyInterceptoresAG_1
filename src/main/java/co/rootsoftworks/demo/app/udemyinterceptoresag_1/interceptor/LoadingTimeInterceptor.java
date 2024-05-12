package co.rootsoftworks.demo.app.udemyinterceptoresag_1.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("interceptorTiempo")
public class LoadingTimeInterceptor implements HandlerInterceptor{

    private static final Logger log = LoggerFactory.getLogger(LoadingTimeInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        log.info("LoadingTimeInterceptor: preHandle() entrando..." + method.getMethod().getName());
        
        Long startTime = System.currentTimeMillis();
        request.setAttribute("start", startTime);

        Random rand = new Random();
        int time = rand.nextInt(2000);
        Thread.sleep(time);

        //Creando respuesta de error
        Map<String, String> json = new HashMap<>();
        json.put("error", "No autorizado");
        json.put("date", new Date().toString());

        //Convirtiendo a String (RestController lo hace automático, pero esto no es RestController)
        ObjectMapper mapper = new ObjectMapper();
        String json2string = mapper.writeValueAsString(json);

        //Indicamos que se va a devolver un JSON y se agrega el status 403 Forbidden
        response.setContentType("application/json");
        response.setStatus(403);
        response.getWriter().write(json2string);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        
        Long endTime = System.currentTimeMillis();
        Long startTime = (Long) request.getAttribute("start");

        Long res = endTime-startTime;
        
        log.info("Tiempo transcurrido: " + res + " ms");
        log.info("LoadingTimeInterceptor: postHandle() saliendo..." + ((HandlerMethod) handler).getMethod().getName());
    }

    
    
}
