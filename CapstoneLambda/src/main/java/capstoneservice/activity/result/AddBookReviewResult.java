package capstoneservice.activity.result;

import capstoneservice.models.ReviewModel;

import java.util.ArrayList;
import java.util.List;

public class AddBookReviewResult {

    private final List<ReviewModel> reviewModelsList;

    public AddBookReviewResult(List<ReviewModel> reviewModelsList) {
        this.reviewModelsList = reviewModelsList;
    }

    public List<ReviewModel> getReviewModelsList() {
        return reviewModelsList;
    }
    @Override
    public String toString() {
        return "AddBookReviewResult{" +
                "reviewModelsList=" + reviewModelsList +
                '}';
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<ReviewModel> reviewModelsList;
        public Builder withReviewsList(List<ReviewModel> list) {
            this.reviewModelsList = new ArrayList<>(list);
            return this;
        }
        public AddBookReviewResult build() {
            return new AddBookReviewResult(reviewModelsList);
        }
    }
}
