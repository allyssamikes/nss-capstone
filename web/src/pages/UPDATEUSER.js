import CapstoneClient from '../api/CAPSTONECLIENT';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create playlist page of the website.
 */
class UpdateUser extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'update'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        document.getElementById('update-user').addEventListener('click', this.update);

        this.header.addHeaderToPage();

        this.client = new CapstoneClient();
    }

        async update(evt) {
            evt.preventDefault();

            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = ``;
            errorMessageDisplay.classList.add('hidden');

            const updateButton = document.getElementById('update-user');
            const origButtonText = updateButton.innerText;
            updateButton.innerText = 'Loading...';

            const userId = document.getElementById('user-userId').value;
            const name = document.getElementById('user-name').value;

            const user = await this.client.updateUser(userId, name, (error) => {
            updateButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
            });

            this.dataStore.set('user', user);
            updateButton.innerText = 'Complete';
            updateButton.innerText = 'Update User';

        }

}
/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const updateUser = new UpdateUser();
    updateUser.mount();
};

window.addEventListener('DOMContentLoaded', main);