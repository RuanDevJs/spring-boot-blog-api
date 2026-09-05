package com.ruandevjs.spring_boot.blog_api.modules.users;

import com.ruandevjs.spring_boot.blog_api.modules.users.useCases.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController()
@RequestMapping("/users")
public class UserController {
    @Autowired
    private CreateNewUserUseCase createUserUseCase;
    @Autowired
    private FindUserByIdUseCase findUserByIdUseCase;

    @Autowired
    private UpdateOneByIdUseCase updateOneByIdUseCase;

    @Autowired
    private FindAllUseCase findAllUseCase;

    @Autowired
    private DeleteOneById deleteOneById;

    @GetMapping()
    public List<UserResponseDTO> findAll(){
        List<UserEntity> userEntityList = this.findAllUseCase.execute();
        List<UserResponseDTO> userResponseDTOS = new ArrayList<UserResponseDTO>();

        userEntityList.forEach(userEntity -> {
            UserResponseDTO userResponseDTO = new UserResponseDTO(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getCreatedAt());
            userResponseDTOS.add(userResponseDTO);
        });

        return userResponseDTOS;
    }

    @PostMapping()
    public UserEntity create(@RequestBody @Valid UserEntity payload){
        return this.createUserUseCase.execute(payload);
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> findById(@PathVariable UUID id){
        return this.findUserByIdUseCase.execute(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOneById(@PathVariable UUID id, @RequestBody Optional<UserEntity> payload){
        this.updateOneByIdUseCase.execute(id, payload);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOneById(@PathVariable UUID id){
        this.deleteOneById.execute(id);
        return ResponseEntity.status(204).body(null);
    }

}
