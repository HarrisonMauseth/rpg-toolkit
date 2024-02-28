package com.harrisonmauseth.rpgtoolkit.controller;

import com.harrisonmauseth.rpgtoolkit.dao.MagicItemDao;
import com.harrisonmauseth.rpgtoolkit.dao.UserDao;
import com.harrisonmauseth.rpgtoolkit.exception.DaoException;
import com.harrisonmauseth.rpgtoolkit.model.MagicItem;
import com.harrisonmauseth.rpgtoolkit.model.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TestController {

    private UserDao userDao;
    private MagicItemDao magicItemDao;

    public TestController(UserDao userDao, MagicItemDao magicItemDao) {
        this.userDao = userDao;
        this.magicItemDao = magicItemDao;
    }

    @GetMapping("/whoami")
    public String whoami(Principal principal) {
        return principal.getName();
    }

    @GetMapping(path = "/users")
    public List<UserEntity> list() {
        try {
            return userDao.getUsers();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/items")
    public List<MagicItem> getMagic() {
        try {
            return magicItemDao.getMagicItems();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
