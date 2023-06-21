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
        this.bindClassMethods(['mount', 'submit'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }
    /**
     * Add the header to the page and load the Client.
     */
    mount() {
        document.getElementById('add-book').addEventListener('click', this.submit);
       this.header.addHeaderToPage();

        this.client = new CapstoneClient();
    }

        async submit(evt) {
            evt.preventDefault();

            const isbn = document.getElementById("isbn");
           const book = await this.client.getBook(isbn);
           this.dataStore.set('book', book);

            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = ``;
            errorMessageDisplay.classList.add('hidden');

            const createButton = document.getElementById('add-book');
            const origButtonText = createButton.innerText;
            createButton.innerText = 'Loading...';

            const userId = document.getElementById('userId').value;
            const list = document.getElementById("list-type").value;


            if (list === "currentlyReading" ) {
                 await this.client.addBookToCurrentlyReading(userId, isbn, (error) => {
                createButton.innerText = origButtonText;
                const errorMessageDisplay = document.getElementById('error-message');
                errorMessageDisplay.innerText = `Error: ${error.message}`;
                errorMessageDisplay.classList.remove('hidden');
            });
            }


            if (list === "toReadList") {
             await this.client.addBookToToReadList(userId, isbn, (error) => {
                createButton.innerText = origButtonText;
                const errorMessageDisplay = document.getElementById('error-message');
                errorMessageDisplay.innerText = `Error: ${error.message}`;
                errorMessageDisplay.classList.remove('hidden');
            });
            }

            if (list === "readList" ) {
            await this.client.addBookToReadList(userId, isbn, (error) => {
                createButton.innerText = origButtonText;
                const errorMessageDisplay = document.getElementById('error-message');
                errorMessageDisplay.innerText = `Error: ${error.message}`;
                errorMessageDisplay.classList.remove('hidden');
            });
            }

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