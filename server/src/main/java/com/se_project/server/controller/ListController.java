package com.se_project.server.controller;


import com.se_project.server.model.ListEntity;
import com.se_project.server.repository.ListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lists")
public class ListController {
    private final ListRepository listRepository;

    public ListController(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @GetMapping
    public List<ListEntity> getAllLists() {
        return listRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListEntity> getListById(@PathVariable Long id) {
        return listRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<ListEntity> createList(@RequestBody ListEntity listEntity) {
        ListEntity savedList = listRepository.save(listEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedList);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ListEntity> updateList(
            @PathVariable Long id, @RequestBody ListEntity listEntity) {
        return listRepository.findById(id)
                .map(existingList -> {
                    existingList.setTitle(listEntity.getTitle());
                    ListEntity updatedList = listRepository.save(existingList);
                    return ResponseEntity.ok(updatedList);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteList(@PathVariable Long id) {
//        return listRepository.findById(id)
//                .map(existingList -> {
//                    listRepository.delete(existingList);
//                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//                })
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }
}
