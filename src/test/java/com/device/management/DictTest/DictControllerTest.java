package com.device.management.DictTest;

import com.device.management.controller.DictController;
import com.device.management.dto.ApiResponse;
import com.device.management.dto.DictItemDTO;
import com.device.management.service.DictService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DictController.class)
class DictControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DictService dictService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    @WithMockUser
    void testGetDictItemsByTypeCode_Success() throws Exception {
        // 准备测试数据
        List<DictItemDTO> mockLists = List.of(new DictItemDTO(1L, "使用可", 1));
        ApiResponse<List<DictItemDTO>> expectedResponse = ApiResponse.success(mockLists);

        // 模拟服务层返回
        when(dictService.getDictItemsByTypeCode("USER_TYPE")).thenReturn(expectedResponse);

        // 执行请求并验证结果
        mockMvc.perform(get("/dict/items")
                .param("dictTypeCode", "USER_TYPE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("成功"));
    }

    @Test
    @WithMockUser
    void testGetDictItemsByTypeCode_EmptyDictTypeCode() throws Exception {
        // 模拟服务层返回错误
        ApiResponse<List<DictItemDTO>> errorResponse = ApiResponse.error(
            40001,
            "dictTypeCodeは文字列で指定してください"
        );
        when(dictService.getDictItemsByTypeCode("")).thenReturn(errorResponse);

        // 测试空参数
        mockMvc.perform(get("/dict/items")
                .param("dictTypeCode", ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(40001));
    }

    @Test
    @WithMockUser
    void testGetDictItemsByTypeCode_NullDictTypeCode() throws Exception {
        // 测试null参数 - 期望返回200 HTTP状态码，但业务码为40001
        mockMvc.perform(get("/dict/items"))
                .andExpect(status().isOk())  // HTTP状态码仍为200
                .andExpect(jsonPath("$.code").value(40001));  // 业务码40001
    }
}
