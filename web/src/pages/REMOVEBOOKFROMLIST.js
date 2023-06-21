import CapstoneClient from '../api/CAPSTONECLIENT';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the AddActivityToItinerary page of the website.
 */
class RemoveBookFromList extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit'], this);
        this.dataStore = new DataStore();
        this.dataStoreSearch = new DataStore();
        this.header = new Header(this.dataStore);
       // this.clientLoaded();
        console.log("constructor")
    }
    /**
     * Add the header to the page and load the Client.
     */
    mount() {
        document.getElementById('remove-book').addEventListener('click', this.submit);
        this.header.addHeaderToPage();

        this.client = new CapstoneClient();
    }
//        async clientLoaded() {
//            const urlParams = new URLSearchParams(window.location.search);
//            const isbn = urlParams.get("isbn");
//            const book = await this.client.getBook(isbn);
//            this.dataStore.set('book', book);
//
//            let Input1 = document.getElementById("isbn");
//            Input1.value = isbn;
//            }

        async submit(evt) {
            evt.preventDefault();

            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = ``;
            errorMessageDisplay.classList.add('hidden');

            const removeButton = document.getElementById('remove-book');
            const origButtonText = removeButton.innerText;
            removeButton.innerText = 'Loading...';


            const list = document.getElementById('list-type').value;
            const isbn =document.getElementById('isbn').value;
           console.log("54");
           console.log(isbn);

               if (list === "currentlyReading" ) {
                     await this.client.removeBookFromCurrentlyReading(userId, isbn, (error) => {
                            removeButton.innerText = origButtonText;
                            const errorMessageDisplay = document.getElementById('error-message');
                            errorMessageDisplay.innerText = `Error: ${error.message}`;
                            errorMessageDisplay.classList.remove('hidden');
                        });
                        }
              if (list === "toReadList") {
                      await this.client.removeBookFromToReadList(userId, isbn, (error) => {
                            removeButton.innerText = origButtonText;
                            const errorMessageDisplay = document.getElementById('error-message');
                            errorMessageDisplay.innerText = `Error: ${error.message}`;
                            errorMessageDisplay.classList.remove('hidden');
              });
                        }

            removeButton.innerText = 'Complete';
            removeButton.innerText = 'Remove';
        }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const removeBookFromList = new RemoveBookFromList();
    removeBookFromList.mount();
};

window.addEventListener('DOMContentLoaded', main);