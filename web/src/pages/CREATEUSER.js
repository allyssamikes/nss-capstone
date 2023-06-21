import CapstoneClient from '../api/CAPSTONECLIENT';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create playlist page of the website.
 */
class CreateUser extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        document.getElementById('create-user').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new CapstoneClient();
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

            const createButton = document.getElementById('create-user');
            const origButtonText = createButton.innerText;
            createButton.innerText = 'Loading...';

            const userId = document.getElementById('user-userId').value;
            const name = document.getElementById('user-name').value;

            const user = await this.client.createUser(userId, name, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
            });

            this.dataStore.set('user', user);
            createButton.innerText = 'Complete';
            createButton.innerText = 'Create User';
        }
        }

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createUser = new CreateUser();
    createUser.mount();
};

window.addEventListener('DOMContentLoaded', main);