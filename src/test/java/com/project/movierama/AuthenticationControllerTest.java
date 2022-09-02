package com.project.movierama;

import com.project.movierama.dtos.UserRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class AuthenticationControllerTest extends BasicTestConfig {

    @Test
    public void loginUserTest() throws Exception {

        UserRequestDto userRequestDto = getUserRequestDto("123");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userRequestDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void loginUserFailedTest() throws Exception {

        UserRequestDto userRequestDto = getUserRequestDto("456");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userRequestDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    private UserRequestDto getUserRequestDto(String password) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setUsername("tommy");
        userRequestDto.setPassword(password);
        return userRequestDto;
    }

}
