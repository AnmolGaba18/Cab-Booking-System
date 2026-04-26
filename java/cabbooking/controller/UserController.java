package cabbooking.controller;

import cabbooking.model.User;
import cabbooking.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository r){
        repo=r;
    }

    @PostMapping("/add")
    public User addUser(@RequestParam Long userId,
                        @RequestParam String name){

        User u=new User();
        u.setId(userId);
        u.setName(name);

        return repo.save(u);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return repo.findAll();
    }
}