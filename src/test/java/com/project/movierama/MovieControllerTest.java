package com.project.movierama;

import com.project.movierama.dtos.MovieRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WithMockUser(username="tommy")
public class MovieControllerTest extends BasicTestConfig {

    //@Ignore //this test after running once OR change values in QuestionResponseDto fields
    @Test
    public void saveMovieTest() throws Exception {

        MovieRequestDto movieRequestDto = getMovieRequestDto();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movieRequestDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    private MovieRequestDto getMovieRequestDto() {
        MovieRequestDto movieRequestDto = new MovieRequestDto();
        movieRequestDto.setTitle("1968");
        movieRequestDto.setPlot("Dummy Plot");
        movieRequestDto.setReleaseYear((short) 2020);
        movieRequestDto.setUserId(1L);
        return movieRequestDto;
    }
}
