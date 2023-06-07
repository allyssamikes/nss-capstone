package capstoneservice.activity.result;

import capstoneservice.models.UserModel;

public class UpdateUserResult {

    private final UserModel userModel;


    public UpdateUserResult(UserModel userModel) {
        this.userModel = userModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    @Override
    public String toString() {
        return "UpdateUserResult{" +
                "userModel=" + userModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UserModel model;

        public Builder withUserModel(UserModel model) {
            this.model = model;
            return this;
        }

        public UpdateUserResult build() {
            return new UpdateUserResult(model);
        }
    }
}
