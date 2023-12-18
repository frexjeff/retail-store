package com.joa.retailstorev1;

import com.joa.retailstorev1.model.Category;
import com.joa.retailstorev1.services.CategoryService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryResource.class)
class CategoryResourceTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;

    Category category;

    @BeforeEach
    public void setup() {
        category = Category.builder()
                .id(1L)
                .name("Category 1")
                .build();
    }


    @Test
    void testGetAllCategory() throws Exception {
        when(categoryService.getAll()).thenReturn(List.of(category));
        this.mockMvc.perform(
                get("/category"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Category 1\"}]"));
        Mockito.verify(categoryService, Mockito.times(1)).getAll();
    }

    @SneakyThrows
    @Test
    void testGetCategoryById() {
        when(categoryService.get(any())).thenReturn(category);
        this.mockMvc.perform(get("/category/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Category 1\"}"));
        Mockito.verify(categoryService, Mockito.times(1)).get(1L);
    }

    @SneakyThrows
    @Test
    void testCreateCategory() {
        when(categoryService.create(any(Category.class)))
                .thenReturn(category);

        mockMvc.perform(post("/category")
                        .content(mapper.writeValueAsString(category))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(mockRes -> {
                    assertEquals("{\"id\":1,\"name\":\"Category 1\"}", mockRes.getResponse().getContentAsString());
                });
    }

}