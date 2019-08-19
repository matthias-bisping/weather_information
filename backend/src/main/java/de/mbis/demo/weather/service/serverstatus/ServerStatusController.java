package de.mbis.demo.weather.service.serverstatus;

import de.mbis.demo.weather.service.model.ServerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("rest/")
public class ServerStatusController {

    @Autowired
    private HttpSession httpSession;

    @RequestMapping("/status")
    public ServerStatus getCurrentStatus() {
        return ServerStatus.ok(httpSession.getId());
    }

}
