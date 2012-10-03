package com.jglitter.web;

import com.google.common.base.Preconditions;
import com.jglitter.domain.User;
import com.jglitter.domain.Users;
import com.jglitter.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    public @ResponseBody void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Users getUsers() {
        return userService.findAllUsers();
    }

    @RequestMapping(value = "/followers/{followerId}/{userToFollowId}", method = RequestMethod.POST)
    public @ResponseBody
    void addUserToFollow(@PathVariable String followerId, @PathVariable String userToFollowId) {


    }

    @RequestMapping(value = "/followees/{followerId}", method = RequestMethod.GET)
    public Users getFollowees(@PathVariable String followerId) {
        Users users = new Users();
        users.add(new User("brad@vmware.com", "brad"));
        return users;
    }

    /**
     * Handle exceptions by returning an appropriate HttpStatus code and reason
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public void handleException(final Exception e) {

        Preconditions.checkNotNull(e);

        logger.error("Unhandled exception: ", e);
    }

}
