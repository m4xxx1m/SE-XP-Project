package com.se_project.server.controller;


import com.se_project.server.model.ListEntity;
import com.se_project.server.repository.ListRepository;
import com.se_project.server.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lists")
public class ListController {
    private final ListRepository listRepository;
    private final UserRepository userRepository;

    public ListController(ListRepository listRepository, UserRepository userRepository) {
        this.listRepository = listRepository;
        this.userRepository = userRepository;
    }


    @GetMapping
    public ResponseEntity<List<ListEntity>> getAllLists(@RequestParam Long userId) {
        List<ListEntity> lists = listRepository.findByUserId(userId);
        return ResponseEntity.ok(lists);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ListEntity> getListById(@PathVariable Long id, @RequestParam Long userId) {
        return listRepository.findById(id)
                .filter(list -> list.getUser().getId().equals(userId))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<ListEntity> createList(@RequestParam Long userId, @RequestBody ListEntity listEntity) {
        return userRepository.findById(userId)
                .map(user -> {
                    listEntity.setUser(user);
                    ListEntity savedList = listRepository.save(listEntity);
                    return ResponseEntity.status(HttpStatus.CREATED).body(savedList);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListEntity> updateList(
            @PathVariable Long id, @RequestParam Long userId, @RequestBody ListEntity listEntity) {
        return listRepository.findById(id)
                .filter(list -> list.getUser().getId().equals(userId))
                .map(existingList -> {
                    existingList.setTitle(listEntity.getTitle());
                    ListEntity updatedList = listRepository.save(existingList);
                    return ResponseEntity.ok(updatedList);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteList(@PathVariable Long id, @RequestParam Long userId) {
        return listRepository.findById(id)
                .filter(list -> list.getUser().getId().equals(userId))
                .map(existingList -> {
                    listRepository.delete(existingList);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
