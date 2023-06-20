    import CapstoneClient from '../api/CAPSTONECLIENT';
    import Header from '../components/header';
    import BindingClass from '../util/bindingClass';
    import DataStore from '../util/DataStore';

    /**
     * Logic needed for the create playlist page of the website.
     */
    class DeleteUser extends BindingClass {
        constructor() {
            super();
            this.bindClassMethods(['mount', 'delete'], this);
            this.dataStore = new DataStore();
            this.header = new Header(this.dataStore);
            console.log("delete constructor")
        }

        mount() {
            document.getElementById('delete-user').addEventListener('click', this.delete);

            this.header.addHeaderToPage();

            this.client = new CapstoneClient();
        }
    async delete(evt) {
                    evt.preventDefault();

                    const errorMessageDisplay = document.getElementById('delete-message');
                    errorMessageDisplay.innerText = ``;
                    errorMessageDisplay.classList.add('hidden');

                    const deleteButton = document.getElementById('delete-user');
                    const origButtonText = deleteButton.innerText;
                    deleteButton.innerText = 'Loading...';

                    const userId = document.getElementById('delete-userId').value;
                    console.log("delete 36")
                    await this.client.deleteUser(userId, (error) => {
                    deleteButton.innerText = origButtonText;
                    errorMessageDisplay.innerText = `Error: ${error.message}`;
                    errorMessageDisplay.classList.remove('hidden');
                    });

                    deleteButton.innerText = 'Complete';
                    deleteButton.innerText = 'Delete User';

                }

   }
                /**
                 * Main method to run when the page contents have loaded.
                 */
                const main = async () => {
                    const deleteUser = new DeleteUser();
                    deleteUser.mount();
                };

                window.addEventListener('DOMContentLoaded', main);