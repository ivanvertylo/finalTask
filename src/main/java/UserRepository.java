public interface UserRepository {
    User findUserByLogin(String login);
}
