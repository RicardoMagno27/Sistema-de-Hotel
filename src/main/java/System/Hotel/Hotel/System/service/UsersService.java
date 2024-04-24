package System.Hotel.Hotel.System.service;

import java.security.NoSuchAlgorithmException;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;

import System.Hotel.Hotel.System.execption.CriptoExistsException;
import System.Hotel.Hotel.System.execption.EmailExistsException;
import System.Hotel.Hotel.System.execption.ServiceException;
import System.Hotel.Hotel.System.model.Users;
import System.Hotel.Hotel.System.repository.UsersRepository;
import System.Hotel.Hotel.System.util.Util;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public void SalveUser(Users users) throws Exception {

        try {

            if (usersRepository.findByUsername(users.getUsername()) != null) {
                throw new EmailExistsException("Este usuario já esta cadastrado: " + users.getUsername());
            }

            users.setPassword(Util.md5(users.getPassword()));

        } catch (NoSuchAlgorithmException e) {
            throw new CriptoExistsException("Error na criptografia da senha");
        }
        usersRepository.save(users);
    }

    public Users loginUsers(String username, String password) throws ServiceException {
        return usersRepository.buscarlogin(username, password);
    }

}
