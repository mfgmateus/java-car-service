package br.com.projetas.carservice.web.rest;

import br.com.projetas.carservice.CarServiceApplication;
import br.com.projetas.carservice.TestUtil;
import br.com.projetas.carservice.service.CarService;
import br.com.projetas.carservice.service.dto.CarDTO;
import br.com.projetas.carservice.service.mapper.CarMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarServiceApplication.class)
public class CarResourceIntTest {

    private static final String DEFAULT_BRAND = "DEFAULT_BRAND";
    private static final String DEFAULT_COLOR = "DEFALT_COLOR";
    private static final LocalDate DEFAULT_DATE = LocalDate.now();
    private static final String DEFAULT_DESCRIPTION = "DEFAULT_DESCRIPTION";
    private static final String DEFAULT_MODEL = "DEFAULT_MODEL";
    private static final Boolean DEFAULT_NEW_CAR = Boolean.TRUE;
    private static final Double DEFAULT_PRICE = 10000d;
    private static final Integer DEFAULT_YEAR = 2018;

    private static final String UPDATED_BRAND = "UPDATED_BRAND";
    private static final String UPDATED_COLOR = "UPDATED_COLOR";
    private static final LocalDate UPDATED_DATE = LocalDate.now();
    private static final String UPDATED_DESCRIPTION = "UPDATED_DESCRIPTION";
    private static final String UPDATED_MODEL = "UPDATED_MODEL";
    private static final Boolean UPDATED_NEW_CAR = Boolean.FALSE;
    private static final Double UPDATED_PRICE = 20000d;
    private static final Integer UPDATED_YEAR = 2019;
    private static final String REGEX_UNIQ_BRAND = "$.brand";
    private static final String REGEX_UNIQ_COLOR = "$.color";
    private static final String REGEX_UNIQ_DESCRIPTION = "$.description";
    private static final String REGEX_UNIQ_MODEL = "$.model";
    private static final String REGEX_UNIQ_NEW_CAR = "$.newCar";
    private static final String REGEX_UNIQ_PRICE = "$.price";
    private static final String REGEX_UNIQ_YEAR = "$.year";
    private static final String REGEX_UNIQ_ID = "$.id";

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CarService carService;

    @Autowired
    private CarMapper carMapper;

    private MockMvc restMockMvc;

    private CarResource carResource;


    private CarDTO createEntity() {
        return CarDTO.builder()
                .brand(DEFAULT_BRAND)
                .color(DEFAULT_COLOR)
                .creationDate(DEFAULT_DATE)
                .description(DEFAULT_DESCRIPTION)
                .model(DEFAULT_MODEL)
                .newCar(DEFAULT_NEW_CAR)
                .price(DEFAULT_PRICE)
                .year(DEFAULT_YEAR)
                .build();
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        carResource = new CarResource(carService);
        this.restMockMvc = MockMvcBuilders.standaloneSetup(carResource)
                .build();
    }

    @Test
    public void shouldCreate() throws Exception {

        CarDTO carDTO = createEntity();
        restMockMvc.perform(post("/api/car")
                .content(TestUtil.convertObjectToJsonBytes(carDTO))
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(REGEX_UNIQ_BRAND).value(carDTO.getBrand()))
                .andExpect(jsonPath(REGEX_UNIQ_COLOR).value(carDTO.getColor()))
                .andExpect(jsonPath(REGEX_UNIQ_DESCRIPTION).value(carDTO.getDescription()))
                .andExpect(jsonPath(REGEX_UNIQ_MODEL).value(carDTO.getModel()))
                .andExpect(jsonPath(REGEX_UNIQ_NEW_CAR).value(carDTO.getNewCar()))
                .andExpect(jsonPath(REGEX_UNIQ_PRICE).value(carDTO.getPrice()))
                .andExpect(jsonPath(REGEX_UNIQ_YEAR).value(carDTO.getYear()));

    }

    @Test
    public void shouldUpdate() throws Exception {

        CarDTO carDTO = createEntity();
        CarDTO result = carService.create(carDTO);

        carDTO.setId(result.getId());
        carDTO.setBrand(UPDATED_BRAND);
        carDTO.setColor(UPDATED_COLOR);
        carDTO.setCreationDate(UPDATED_DATE);
        carDTO.setDescription(UPDATED_DESCRIPTION);
        carDTO.setModel(UPDATED_MODEL);
        carDTO.setNewCar(UPDATED_NEW_CAR);
        carDTO.setPrice(UPDATED_PRICE);
        carDTO.setYear(UPDATED_YEAR);

        restMockMvc.perform(put("/api/car")
                .content(TestUtil.convertObjectToJsonBytes(carDTO))
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath(REGEX_UNIQ_ID).value(carDTO.getId().intValue()))
                .andExpect(jsonPath(REGEX_UNIQ_BRAND).value(carDTO.getBrand()))
                .andExpect(jsonPath(REGEX_UNIQ_COLOR).value(carDTO.getColor()))
                .andExpect(jsonPath(REGEX_UNIQ_DESCRIPTION).value(carDTO.getDescription()))
                .andExpect(jsonPath(REGEX_UNIQ_MODEL).value(carDTO.getModel()))
                .andExpect(jsonPath(REGEX_UNIQ_NEW_CAR).value(carDTO.getNewCar()))
                .andExpect(jsonPath(REGEX_UNIQ_PRICE).value(carDTO.getPrice()))
                .andExpect(jsonPath(REGEX_UNIQ_YEAR).value(carDTO.getYear()));

    }


    @Test
    public void shouldFindAll() throws Exception {

        CarDTO carDTO = carService.create(createEntity());

        restMockMvc.perform(get("/api/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").value(hasItem(carDTO.getId().intValue())))
                .andExpect(jsonPath("$.[*].brand").value(hasItem(carDTO.getBrand())))
                .andExpect(jsonPath("$.[*].color").value(hasItem(carDTO.getColor())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(carDTO.getDescription())))
                .andExpect(jsonPath("$.[*].model").value(hasItem(carDTO.getModel())))
                .andExpect(jsonPath("$.[*].newCar").value(hasItem(carDTO.getNewCar())))
                .andExpect(jsonPath("$.[*].price").value(hasItem(carDTO.getPrice())))
                .andExpect(jsonPath("$.[*].year").value(hasItem(carDTO.getYear())));

    }


    @Test
    public void shouldFindOne() throws Exception {

        CarDTO carDTO = carService.create(createEntity());

        restMockMvc.perform(get("/api/car/{id}", carDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath(REGEX_UNIQ_ID).value(carDTO.getId().intValue()))
                .andExpect(jsonPath(REGEX_UNIQ_BRAND).value(carDTO.getBrand()))
                .andExpect(jsonPath(REGEX_UNIQ_COLOR).value(carDTO.getColor()))
                .andExpect(jsonPath(REGEX_UNIQ_DESCRIPTION).value(carDTO.getDescription()))
                .andExpect(jsonPath(REGEX_UNIQ_MODEL).value(carDTO.getModel()))
                .andExpect(jsonPath(REGEX_UNIQ_NEW_CAR).value(carDTO.getNewCar()))
                .andExpect(jsonPath(REGEX_UNIQ_PRICE).value(carDTO.getPrice()))
                .andExpect(jsonPath(REGEX_UNIQ_YEAR).value(carDTO.getYear()));

    }

    @Test
    public void shouldDelete() throws Exception {

        CarDTO carDTO = carService.create(createEntity());

        restMockMvc.perform(delete("/api/car/{id}", carDTO.getId()))
                .andExpect(status().isOk());

        CarDTO result = carService.findOne(carDTO.getId());

        Assert.assertNull(result);

    }


}
