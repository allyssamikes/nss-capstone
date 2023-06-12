import CapstoneClient from '../api/CAPSTONECLIENT';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create playlist page of the website.
 */
class WriteReview extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'clientLoaded', 'submit'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        document.getElementById('create-user').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new CapstoneClient();
    }

        async clientLoaded() {
            const urlParams = new URLSearchParams(window.location.search);
            const UUIDOfEntity = urlParams.get("UUIDOfEntity");
            const isbn = urlParams.get("isbn");
            const title = urlParams.get("title");
            const director = urlParams.get("director");
            const titleM = urlParams.get("titleM");
            const directorM = urlParams.get("directorM");
            const book = await this.client.getBook(isbn);
            const tvShow = await this.client.getTVShow(title, director);
            const movie = await this.client.getMovie(titleM, directorM);
            this.dataStore.set('book', book);
            this.dataStore.set('tvShow', tvShow);
            this.dataStore.set('movie', movie);
        }
        /**
         * Method to run when the create activity submit button is pressed. Call the MusicPlaylistService to create the
         * activity.
         */
        async submit(evt) {
            evt.preventDefault();

            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = ``;
            errorMessageDisplay.classList.add('hidden');

            const createButton = document.getElementById('write-review');
            const origButtonText = createButton.innerText;
            createButton.innerText = 'Loading...';

            const userId = document.getElementById('review-userId').value;
            const rating = document.getElementById('review-rating').value;
            const review = document.getElementById('review-review').value;
            const UUIDOfEntity = tvShow.UUIDOfEntity;

            const user = await this.client.writeReview(userId, rating, review, UUIDOfEntity);
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');

            this.dataStore.set('user', user);
            createButton.innerText = 'Complete';
            createButton.innerText = 'Write Review';
        }

}
/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const writeReview = new WriteReview();
    writeReview.mount();
};

window.addEventListener('DOMContentLoaded', main);