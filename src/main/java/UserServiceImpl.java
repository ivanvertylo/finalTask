import org.apache.commons.codec.digest.DigestUtils;

public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl() {
        userRepository = new UserRepositoryImpl();
    }

    @Override
    public User validateUser(String login, String password) {
        User user = userRepository.findUserByLogin(login);
        if (user.getPassword() != null){
            return user.getPassword().equals(DigestUtils.md5Hex(password)) ? user : null;
        }
        return null;
    }
}
