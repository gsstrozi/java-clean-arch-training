package br.com.training.cleanarch.entrypoint;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class HelloController {

    @Autowired
    @Qualifier("dbJdbcTemplate")
    NamedParameterJdbcTemplate dbJdbcTemplate;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem de sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Aconteceu um erro inesperado"),
    })
    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
    public String hello() {
        dbJdbcTemplate.update("INSERT INTO TEST_INTEGRATION_SCRIPT VALUES ('TESTE 1')", new HashMap<String, String>());

        return "Hello, this is my first controller";
    }

}
