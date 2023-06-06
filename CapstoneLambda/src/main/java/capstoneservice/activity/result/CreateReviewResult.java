package capstoneservice.activity.result;

import capstoneservice.models.ReviewModel;

public class CreateReviewResult {
    private final ReviewModel review;

    public CreateReviewResult(ReviewModel review) {
        this.review = review;
    }

    public ReviewModel getReview() {
        return review;
    }

    @Override
    public String toString() {
        return "CreateReviewResult{" +
                "review=" + review +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ReviewModel review;

        public Builder withReview(ReviewModel review) {
            this.review = review;
            return this;
        }

        public CreateReviewResult build() {
            return new CreateReviewResult(review);
        }
    }
}
