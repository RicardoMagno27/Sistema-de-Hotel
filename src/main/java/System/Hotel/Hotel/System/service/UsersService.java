package System.Hotel.Hotel.System.service;

import java.security.NoSuchAlgorithmException;

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
            if (usersRepository.findByEmail(users.getEmail()) == null) {
                throw new EmailExistsException("Este Email ja Existe" + users.getEmail());
            }
            users.setPassword(Util.md5(users.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            throw new CriptoExistsException("Error na Senha");
        }
        usersRepository.save(users);
    }

    public Users loginUsers(String username, String password) throws ServiceException {
        return usersRepository.buscarlogin(username, password);
    }

}
