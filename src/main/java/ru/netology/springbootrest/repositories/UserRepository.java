package ru.netology.springbootrest.repositories;

import org.springframework.stereotype.Repository;
import ru.netology.springbootrest.entities.Authorities;
import ru.netology.springbootrest.entities.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private Map<User, Set<Authorities>> credentials;

    public UserRepository() {
        this.credentials = new  ConcurrentHashMap<User, Set<Authorities>>();
        this.initialize();
    }

    //инициализируем репозиторий начальными данными
    private void initialize() {
        Set<Authorities> rwd = new HashSet<>();
        rwd.add(Authorities.READ);
        rwd.add(Authorities.WRITE);
        rwd.add(Authorities.DELETE);

        Set<Authorities> rw = new HashSet<>();
        rw.add(Authorities.READ);
        rw.add(Authorities.WRITE);

        Set<Authorities> r = new HashSet<>();
        r.add(Authorities.READ);

        User admin = new User("admin", "Ckj;ysqFlvbycrbqGfhjkm");
        User poweruser = new User("poweruser", "password");
        User user = new User("user", "qwerty");


        credentials.put(admin, rwd);
        credentials.put(poweruser, rw);
        credentials.put(user, r);
    }

    public boolean hasUser(User user){
        return (credentials.containsKey(user));
    }

    public List<Authorities> getUserAuthorities(User user) {
        //если юзер есть в хранилище, возвращаем его права
        if (hasUser(user)){
            return credentials.get(user).stream().toList();
        }
        //иначе вернём пустой лист
        return new ArrayList<Authorities>();
    }
}
