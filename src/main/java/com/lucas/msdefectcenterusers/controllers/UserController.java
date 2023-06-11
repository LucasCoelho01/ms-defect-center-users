package com.lucas.msdefectcenterusers.controllers;

import com.lucas.msdefectcenterusers.dtos.UserRecordDTO;
import com.lucas.msdefectcenterusers.models.UserModel;
import com.lucas.msdefectcenterusers.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDTO userRecordDTO) {
        var productModel = new UserModel();
        BeanUtils.copyProperties(userRecordDTO, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(productModel));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> FindUserById(@PathVariable(value="id") UUID id){
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value="id") UUID id) {
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userRepository.delete(user.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid UserRecordDTO userRecordDto) {
        Optional<UserModel> productO = userRepository.findById(id);
        if(productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var productModel = productO.get();
        BeanUtils.copyProperties(userRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(productModel));
    }
}
