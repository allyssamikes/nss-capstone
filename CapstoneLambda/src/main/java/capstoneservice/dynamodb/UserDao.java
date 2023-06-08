package capstoneservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import capstoneservice.dynamodb.models.User;
import capstoneservice.exceptions.UserNotFoundException;

import javax.inject.Singleton;
import javax.inject.Inject;

@Singleton
public class UserDao {
    private final DynamoDBMapper dynamoDbMapper;

    @Inject
    public UserDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }
    public User getUser(String userId) {
        User user = dynamoDbMapper.load(User.class, userId);
        if (null == user) {
            throw new UserNotFoundException();
        }
        return user;
    }

    public User saveUser(User user) {
        this.dynamoDbMapper.save(user);
        return user;
    }

    public User deleteUser(User user) {
        this.dynamoDbMapper.delete(user);
        return user;
    }

}
