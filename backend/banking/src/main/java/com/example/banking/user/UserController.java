package com.example.banking.user;

import com.example.banking.base.BankingApplicationController;
import com.example.banking.base.ControllerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin(origins = "*")
public class UserController extends BankingApplicationController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        super(ControllerStatus.ONLINE);
        this.userService = userService;

    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @GetMapping(path = "login")
    public User findUserByCredentials(@RequestParam (required = true) String username,
                                      @RequestParam (required = true) String password){
        return userService.findUserByCredentials(username, password);
    }


    @DeleteMapping(path="{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateStudent(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password) {
                userService.updateUser(userId, firstName, lastName, email, username, password);
            }

    
}
