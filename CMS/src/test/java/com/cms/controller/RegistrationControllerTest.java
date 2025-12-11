package com.cms.controller;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.Registration;
import com.cms.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

// 1. Setup the testing context for only the controller layer
@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

    // Inject MockMvc utility object
    @Autowired
    private MockMvc mockMvc;

    // 2. Mock the service layer dependency
    // This isolates the controller: we test the controller logic, not the service/database
    @MockBean
    private RegistrationService registrationService;

    // A test for the successful retrieval of all registrations
    @Test
    public void getAllRegistrations_ShouldReturnListOfRegistrations() throws Exception {
        
        // --- ARRANGE (Setup Mock Data) ---
        // Create mock Registration objects (we only need to set the fields the controller uses)
        Registration reg1 = new Registration();
        reg1.setId(1L);
        reg1.setRegistrationType("Presenter");
        
        Registration reg2 = new Registration();
        reg2.setId(2L);
        reg2.setRegistrationType("Attendee");
        
        List<Registration> expectedRegistrations = Arrays.asList(reg1, reg2);

        // Tell Mockito: when the service.getAllRegistrations() method is called,
        // return our mock list instead of hitting the real database.
        when(registrationService.getAllRegistrations()).thenReturn(expectedRegistrations);

        // --- ACT & ASSERT (Perform the Request and Check Results) ---
        mockMvc.perform(get("/registration") // The endpoint URL from the controller
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // 3. Assert the HTTP status code is 200 OK
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) // Assert content type
                // 4. Assert the size of the returned JSON array
                .andExpect(jsonPath("$", hasSize(2))) 
                // 5. Assert the content of the first element in the returned array
                .andExpect(jsonPath("$[0].id", is(1))) 
                .andExpect(jsonPath("$[0].registrationType", is("Presenter")));
    }

    @Test
    public void getRegistrationById_ShouldReturnNotFoundIfIdDoesNotExist() throws Exception {
        // --- ARRANGE ---
        Long nonExistentId = 99L;

        // Tell Mockito: when asked for a non-existent ID, throw the expected exception.
        // We assume the service throws ResourceNotFoundException (or a similar runtime exception)
        when(registrationService.getRegistrationById(nonExistentId))
            .thenThrow(new ResourceNotFoundException("Registration", "id", nonExistentId));

        // --- ACT & ASSERT ---
        mockMvc.perform(get("/registration/{id}", nonExistentId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); // The @ResponseStatus on ResourceNotFoundException handles this
    }
}