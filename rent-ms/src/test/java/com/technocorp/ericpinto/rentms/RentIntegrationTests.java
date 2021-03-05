package com.technocorp.ericpinto.rentms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technocorp.ericpinto.rentms.model.Film;
import com.technocorp.ericpinto.rentms.model.Rent;
import com.technocorp.ericpinto.rentms.model.User;
import com.technocorp.ericpinto.rentms.repository.RentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class RentIntegrationTests {
    private final String BASE_URL = "/rentapi/rents";

    @MockBean
    private RentRepository rentRepository;

    @Autowired
    MockMvc mockMvc;

    User user = User.builder().id("5fc7ba0ee7e48d20dc2fbf52").name("Éric").email("eric@gmail.com").build();

    Film film = Film.builder().title("Revenge of the Sith").episodeId(6).director("George Lucas").build();

    Rent rent = Rent.builder()
            .id("5fc7ba0ee7e48d20dc2fbf52").user(user).film(film)
            .build();

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Should return status 200 when find all rents")
    void listRents_shouldReturnStatusCode200() throws Exception {
        List<Rent> listRents = new ArrayList<>();
        listRents.add(rent);

        when(rentRepository.findAll()).thenReturn(listRents);

        mockMvc.perform(get(BASE_URL)
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return status 200 when find by id")
    void findRentById_shouldReturnStatusCode200() throws Exception {

        when(rentRepository.findById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(Optional.of(rent));

        mockMvc.perform(get(BASE_URL + "/5fc7ba0ee7e48d20dc2fbf52")
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());

        //verify(rentRepository, times(1)).findById(rent.getId());
    }

    @Test
    @DisplayName("Should return status 404 when rent not found")
    void findRentById_shouldReturnStatusCode404_WhenUserNotFound() throws Exception {
        mockMvc.perform(get(BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should return status 201 when rent created")
    void saveRent_shouldReturnStatusCode201() throws Exception {

        when(rentRepository.insert(rent)).thenReturn(rent);

        mockMvc.perform(post(BASE_URL + "/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rent))
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isCreated());

        //verify(rentRepository, times(1)).insert(any(Rent.class));
    }

    @Test
    @DisplayName("Should return status 200 when rent updated")
    void updateRent_shouldReturnStatusCode200() throws Exception {

        when(rentRepository.findById(rent.getId())).thenReturn(Optional.of(rent));
        when(rentRepository.save(rent)).thenReturn(rent);

        mockMvc.perform(put(BASE_URL + "/5fc7ba0ee7e48d20dc2fbf52?idFilm=2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rent))
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return status 204 when rent deleted")
    void deleteRent_shouldReturnStatusCode204() throws Exception {
        when(rentRepository.findById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(Optional.ofNullable(rent));
        this.mockMvc.perform(delete(BASE_URL + "/5fc7ba0ee7e48d20dc2fbf52"))
                .andExpect(status().isNoContent());

        verify(rentRepository, times(1)).deleteById("5fc7ba0ee7e48d20dc2fbf52");
    }
}
