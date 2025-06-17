package ru.netology.springbootrest.repositories;

import org.springframework.stereotype.Repository;
import ru.netology.springbootrest.entities.Authorities;
import ru.netology.springbootrest.entities.UserAccount;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private Map<UserAccount, Set<Authorities>> credentials;

    public UserRepository() {
        this.credentials = new  ConcurrentHashMap<UserAccount, Set<Authorities>>();
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

        UserAccount admin = new UserAccount("admin", "Ckj;ysqFlvbycrbqGfhjkm");
        UserAccount poweruser = new UserAccount("poweruser", "password");
        UserAccount user = new UserAccount("user", "qwerty");


        credentials.put(admin, rwd);
        credentials.put(poweruser, rw);
        credentials.put(user, r);
    }

    public boolean hasUser(UserAccount userAccount){
        return (credentials.containsKey(userAccount));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        UserAccount userAccount = new UserAccount(user, password);
        //если юзер есть в хранилище, возвращаем его права
        if (hasUser(userAccount)){
            return credentials.get(userAccount).stream().toList();
        }
        //иначе вернём пустой лист
        return new ArrayList<Authorities>();
    }
}
