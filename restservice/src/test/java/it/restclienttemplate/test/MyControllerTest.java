package it.restclienttemplate.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.restclienttemplate.MyController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
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

    @Test
    public void jsonValueNoParamGet() throws Exception {
        this.mockMvc.perform(get("/jsonvaluenoparamget"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void jsonValueNoParamPost() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();


        this.mockMvc.perform(post("/jsonvaluenoparampost"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"responseValue\":\"123\"}"));

    }


}

