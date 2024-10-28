package com.se_project.server;

import com.se_project.server.controller.ListController;
import com.se_project.server.model.ListEntity;
import com.se_project.server.model.User;
import com.se_project.server.repository.ListRepository;
import com.se_project.server.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ListController.class)
public class ServerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ListRepository listRepository;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void testGetAllLists() throws Exception {
		User user = new User();
		user.setId(1L);

		ListEntity listEntity = new ListEntity();
		listEntity.setUser(user);

		given(listRepository.findByUserId(1L)).willReturn(Collections.singletonList(listEntity));

		mockMvc.perform(get("/api/lists")
						.param("userId", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].user.id").value(1));
	}

	@Test
	public void testGetListById() throws Exception {
		User user = new User();
		user.setId(1L);

		ListEntity listEntity = new ListEntity();
		listEntity.setId(1L);
		listEntity.setUser(user);

		given(listRepository.findById(1L)).willReturn(Optional.of(listEntity));

		mockMvc.perform(get("/api/lists/1")
						.param("userId", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1));
	}


	@Test
	public void testCreateList() throws Exception {
		User user = new User();
		user.setId(1L);

		ListEntity listEntity = new ListEntity();
		listEntity.setTitle("New List");

		given(userRepository.findById(1L)).willReturn(Optional.of(user));
		given(listRepository.save(ArgumentMatchers.any(ListEntity.class))).willReturn(listEntity);

		mockMvc.perform(post("/api/lists")
						.param("userId", "1")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"title\":\"New List\"}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.title").value("New List"));
	}



	@Test
	public void testUpdateList() throws Exception {
		User user = new User();
		user.setId(1L);

		ListEntity existingListEntity = new ListEntity();
		existingListEntity.setId(1L);
		existingListEntity.setUser(user);

		ListEntity updatedListEntity = new ListEntity();
		updatedListEntity.setId(1L);
		updatedListEntity.setUser(user);
		updatedListEntity.setTitle("Updated List");
	}

	@Test
	public void testDeleteList() throws Exception {
		User user = new User();
		user.setId(1L);

		ListEntity listEntity = new ListEntity();
		listEntity.setId(1L);
		listEntity.setUser(user);

		given(listRepository.findById(1L)).willReturn(Optional.of(listEntity));
		doNothing().when(listRepository).delete(listEntity);

		mockMvc.perform(delete("/api/lists/1")
						.param("userId", "1"))
				.andExpect(status().isNoContent());
	}
}