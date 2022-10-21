public class UserService {

    public User createUser(User user) {
        user.setId(677L);
        user.setFirstName("Igor");
        user.setLastName(null);
//        user.setLastName("Pushkin");
        return user;
    }
}
