import CapstoneClient from '../api/CAPSTONECLIENT';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the AddActivityToItinerary page of the website.
 */
class AddBookToList extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'clientLoaded', 'submit'], this);
        this.dataStore = new DataStore();
        this.dataStoreSearch = new DataStore();
        this.header = new Header(this.dataStore);
        this.clientLoaded();
    }
    /**
     * Add the header to the page and load the Client.
     */
    mount() {
        document.getElementById('add-book').addEventListener('click', this.submit);
        this.header.addHeaderToPage();

        this.client = new CapstoneClient();
    }
        async clientLoaded() {
            const urlParams = new URLSearchParams(window.location.search);
            const isbn = urlParams.get("isbn");
            const book = await this.client.getBook(isbn);
            this.dataStore.set('book', book);

            let Input1 = document.getElementById("isbn");
            Input1.value = isbn;
            }

        async submit(evt) {
            evt.preventDefault();

            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = ``;
            errorMessageDisplay.classList.add('hidden');

            const createButton = document.getElementById('remove-activity');
            const origButtonText = createButton.innerText;
            createButton.innerText = 'Loading...';


            const email = document.getElementById("email").value;
            const tripName = document.getElementById("itinerary-name").value;
            if(tripName == null || tripName === ''|| email == null || email === '') {
            createButton.innerText = origButtonText;
            return;}

               if (list === currentlyReading ) {
                        const currentlyReading = await this.client.addBookToCurrentlyReading(userId, isbn, (error) => {
                            createButton.innerText = origButtonText;
                            const errorMessageDisplay = document.getElementById('error-message');
                            errorMessageDisplay.innerText = `Error: ${error.message}`;
                            errorMessageDisplay.classList.remove('hidden');
                        });
                        this.dataStore.set('currentlyReading', currentlyReading);
                        }


                        if (list === toReadList) {
                        const toReadList = await this.client.addBookToToReadList(userId, isbn, (error) => {
                            createButton.innerText = origButtonText;
                            const errorMessageDisplay = document.getElementById('error-message');
                            errorMessageDisplay.innerText = `Error: ${error.message}`;
                            errorMessageDisplay.classList.remove('hidden');
                        });
                        this.dataStore.set('toReadList', toReadList);
                        }


            this.dataStore.set('activityList', activities);
            const itinerary = await this.client.getItinerary(email, tripName);
            this.dataStore.set('itinerary', itinerary);
            let activityInput1 = document.getElementById('activity-cityCountry');
            activityInput1.value = "";
            let activityInput2 = document.getElementById('activity-name');
            activityInput2.value = "";
            createButton.innerText = 'Complete';
            createButton.innerText = 'Remove Another Activity';
        }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const addBookToList = new AddBookToList();
    addBookToList.mount();
};

window.addEventListener('DOMContentLoaded', main);