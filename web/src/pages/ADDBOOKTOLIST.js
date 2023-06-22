import CapstoneClient from '../api/CAPSTONECLIENT';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';


class AddBookToList extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit'], this);
        this.dataStore = new DataStore();
        this.dataStoreSearch = new DataStore();
        this.header = new Header(this.dataStore);
        console.log("constructor")
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

            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = ``;
            errorMessageDisplay.classList.add('hidden');

            const createButton = document.getElementById('add-book');
            const origButtonText = createButton.innerText;
            createButton.innerText = 'Loading...';

            const isbn = document.getElementById('isbn').value;
            const userId = document.getElementById('userId').value;
            const list = document.getElementById('list-type').value;

            console.log("42")
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

               createButton.innerText = 'Complete';
               createButton.innerText = 'Add';

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