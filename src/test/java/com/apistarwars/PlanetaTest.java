package com.apistarwars;

import com.apistarwars.controllers.PlanetaController;
import com.apistarwars.repository.PlanetaRepository;
import com.apistarwars.services.PlanetaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = apiStarWarsApplication.class)
@TestPropertySource(locations="classpath:test.properties")
public class PlanetaTest {

    private MockMvc mockMvc;

    @Autowired
    private PlanetaController planetaController;

    @Autowired
    private PlanetaService planetaService;

    @Autowired
    private PlanetaRepository planetaRepository;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(planetaController).build();
        this.mockMvc = MockMvcBuilders.standaloneSetup(planetaService).build();
        this.mockMvc = MockMvcBuilders.standaloneSetup(planetaRepository).build();
    }

    @Test
    public void testGETListPlanetaController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/planeta/")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPOSTSavePlanetaController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/planeta/")
                .param("nome", "Bespin")
                .param("clima", "Árido")
                .param("terreno", "Deserto")
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGETFindByNamePlanetaController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/planeta/name/")
                .param("nome", "Bespin")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGETFindByIdPlanetaController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/planeta/")
                .param("id", "5f1c9039edaadfaa6a24efaf")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteByIdPlanetaController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/planeta/")
                .param("id", "5f1c9039edaadfaa6a24efaf")
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testDeleteByNomePlanetaController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/planeta/")
                .param("nome", "Bespin")
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
