package dynamodb;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dynamodb.models.User;
import exceptions.UserNotFoundException;


public class UserDao {
    private final DynamoDBMapper dynamoDbMapper;

    public UserDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }
    public User getUser(String userId) {
        User user = dynamoDbMapper.load(User.class, userId);
        if (null == user) {
            throw new UserNotFoundException();
            String.format("Could not find User with userID'%s'", userId);
        }
        return user;
    }

    public User saveUser(User user) {
        this.dynamoDbMapper.save(user);
        return user;
    }

}
