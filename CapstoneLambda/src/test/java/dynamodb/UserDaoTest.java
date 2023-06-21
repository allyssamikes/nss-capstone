package dynamodb;

import capstoneservice.dynamodb.UserDao;
import capstoneservice.dynamodb.models.User;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.github.javaparser.utils.Utils.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @InjectMocks
    private UserDao userDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
        userDao = new UserDao(dynamoDBMapper);
    }

    @Test
    public void getBook_withKey_returnsBook(){
        // GIVEN
        String userId = "abcdef";
        when(dynamoDBMapper.load(User.class, userId)).thenReturn(new User());

        // WHEN
        User user = userDao.getUser(userId);

        // THEN
        assertNotNull(user);
        verify(dynamoDBMapper).load(User.class, userId);
    }
}
