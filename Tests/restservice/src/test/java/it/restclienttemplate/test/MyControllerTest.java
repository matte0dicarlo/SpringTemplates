package it.restclienttemplate.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.restclienttemplate.MyController;
import it.restclienttemplate.restservice.MyRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@SpringBootTest
public class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new MyController()).build();
    }

    public static final ObjectMapper mapper = new ObjectMapper();


        @Test
    public void jsonValueNoParamPostTest() throws Exception {
        this.mockMvc.perform(post("/noparampost"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"responseValue\":\"json value without param post\"}"));
    }

        @Test
    public void jsonValueNoParamGetTest() throws Exception {
        this.mockMvc.perform(get("/noparamget"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"responseValue\":\"json value without param get\"}"));
    }

    @Test
    public void jsonValueWithParamPostTest() throws Exception {
        MyRequest req = new MyRequest();
        req.setRequestValue("call value for jsonValueWithParamPost");

        String jsonRequest = mapper.writeValueAsString(req);
        this.mockMvc.perform(post("/parampost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andDo(print())
                .andExpect(content().json("{\"responseValue\":\"json value with param post\"}"))
                .andReturn();
    }

    @Test
    public void jsonValueWithParamGetTest() throws Exception {
        MyRequest req = new MyRequest();
        req.setRequestValue("call value for jsonValueWithParamGet");

        String jsonRequest = mapper.writeValueAsString(req);
        this.mockMvc.perform(get("/paramget")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andDo(print())
                .andExpect(content().json("{\"responseValue\":\"json value with param get\"}"))
                .andReturn();
    }

}

