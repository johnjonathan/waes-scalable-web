/*
 * Copyright 2015-2019 John Silva.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.waes.diff.v1.api.resource;

import com.waes.diff.v1.api.domain.enums.Result;
import com.waes.diff.v1.api.domain.model.PayloadDiffResult;
import com.waes.diff.v1.api.service.PayloadDiffService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PayloadDiffController.class) class PayloadDiffControllerTest {

	@Autowired MockMvc mockMvc;
	@MockBean PayloadDiffService payloadDiffService;

	@Test void getPayloadDiff_shouldReturnEqual() throws Exception {
		given(payloadDiffService.getDiff("001")).willReturn(PayloadDiffResult.create().result(Result.EQUAL));
		mockMvc.perform(get("/v1/diff/{id}", "001")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.result").isNotEmpty());
	}
}
