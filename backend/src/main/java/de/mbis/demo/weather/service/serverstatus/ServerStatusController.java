package de.mbis.demo.weather.service.serverstatus;

import de.mbis.demo.weather.service.model.ServerStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("rest/")
@Api(value = "Server Status")
public class ServerStatusController {

    @Autowired
    private HttpSession httpSession;

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    @ApiOperation(value = "Return current server status and session id", response = ServerStatus.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved server status"),
    }
    )
    public ServerStatus getCurrentStatus() {
        return ServerStatus.ok(httpSession.getId());
    }
}
