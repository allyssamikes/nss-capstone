import CapstoneClient from '../api/CAPSTONECLIENT';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import Authenticator from "../api/authenticator";

/*
The code below this comment is equivalent to...
const EMPTY_DATASTORE_STATE = {
    'search-criteria': '',
    'search-results': [],
};

...but uses the "KEY" constants instead of "magic strings".
The "KEY" constants will be reused a few times below.
*/

const SEARCH_CRITERIA_ISBN = 'isbn';
const SEARCH_CRITERIA_TITLE = 'title';
const SEARCH_CRITERIA_DIRECTOR= 'director';
const SEARCH_CRITERIA_TITLEM = 'titleM';
const SEARCH_CRITERIA_DIRECTORM = 'directorM';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_ISBN]: '',
        [SEARCH_CRITERIA_TITLE]: '',
            [SEARCH_CRITERIA_DIRECTOR]: '',
                [SEARCH_CRITERIA_TITLEM]: '',
                    [SEARCH_CRITERIA_DIRECTORM]: '',
   [SEARCH_RESULTS_KEY]: '',
};

/**
 * Logic needed for the view itinerary page of the website.
 */
class GetItems extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'getBook', 'getTVShow', 'getMovie',  'displaySearchResults', 'getHTMLForSearchResults'], this);

        // Create a enw datastore with an initial "empty" state.
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.displaySearchResults);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        // Wire up the form's 'submit' event and the button's 'click' event to the search method.
        document.getElementById('search-books-form').addEventListener('submit', this.getBook);
        document.getElementById('book-button').addEventListener('click', this.getBook);

        document.getElementById('search-tvshows-form').addEventListener('submit', this.getTVShow);
        document.getElementById('tvshow-button').addEventListener('click', this.getTVShow);

        document.getElementById('search-movies-form').addEventListener('submit', this.getMovie);
        document.getElementById('movie-button').addEventListener('click', this.getMovie);

        this.header.addHeaderToPage();

        this.client = new CapstoneClient();
    }

    /**
     * Uses the client to perform the search,
     * then updates the datastore with the criteria and results.
     * @param evt The "event" object representing the user-initiated event that triggered this method.
     */
    async getBook(evt) {
        // Prevent submitting the form from reloading the page.
        evt.preventDefault();

        const isbn = document.getElementById('isbn').value;

        if (isbn) {
            const results = await this.client.getBook(isbn);
            this.dataStore.setState({
                [SEARCH_CRITERIA_ISBN]: isbn,
                [SEARCH_RESULTS_KEY]: results,
            });
        } else {
            this.dataStore.setState(EMPTY_DATASTORE_STATE);
        }
    }

        async getTVShow(evt) {
            // Prevent submitting the form from reloading the page.
            evt.preventDefault();

            const title = document.getElementById('title').value;
            const director = document.getElementById('director').value;

            if (isbn) {
                const results = await this.client.getTVShow(title, director);
                this.dataStore.setState({
                    [SEARCH_CRITERIA_TRIP_TITLE]: title,
                       [SEARCH_CRITERIA_TRIP_DIRECTOR]: director,
                    [SEARCH_RESULTS_KEY]: results,
                });
            } else {
                this.dataStore.setState(EMPTY_DATASTORE_STATE);
            }
        }

     async getMovie(evt) {
            // Prevent submitting the form from reloading the page.
            evt.preventDefault();

            const titleM = document.getElementById('titleM').value;
            const directorM = document.getElementById('directorM').value;

            if (isbn) {
                const results = await this.client.getMovie(title, director);
                this.dataStore.setState({
                    [SEARCH_CRITERIA_TRIP_TITLEM]: titleM,
                       [SEARCH_CRITERIA_TRIP_DIRECTORM]: directorM,
                    [SEARCH_RESULTS_KEY]: results,
                });
            } else {
                this.dataStore.setState(EMPTY_DATASTORE_STATE);
            }
        }


    /**
     * Pulls search results from the datastore and displays them on the html page.
     */
    async displaySearchResults() {
        const isbn = await this.dataStore.get(SEARCH_CRITERIA_ISBN);
        const title = await this.dataStore.get(SEARCH_CRITERIA_TITLE);
        const director = await this.dataStore.get(SEARCH_CRITERIA_DIRECTOR);
        const titleM = await this.dataStore.get(SEARCH_CRITERIA_TITLEM);
        const directorM = await this.dateStore.get(SEARCH_CRITERIA_DIRECTORM);
        const searchResult = await this.dataStore.get(SEARCH_RESULTS_KEY);

        const searchResultsContainer = document.getElementById('search-results-container');
        const searchCriteriaDisplay = document.getElementById('search-criteria-display');
        const searchResultsDisplay = document.getElementById('search-results-display');

        if (title === '' || isbn === "") {
            searchResultsContainer.classList.add('hidden');
            searchCriteriaDisplay.innerHTML = '';
            searchResultsDisplay.innerHTML = '';
        } else {
            searchResultsContainer.classList.remove('hidden');
            searchCriteriaDisplay.innerHTML = `"${title}"`;
            searchResultsDisplay.innerHTML = await this.getHTMLForSearchResults(searchResult);
        }
        document.getElementById("search-books-form").reset();
        document.getElementById("search-tvshows-form").reset();
       document.getElementById("search-movies-form").reset();
    }

    /**
     * Create appropriate HTML for displaying searchResults on the page.
     * @param searchResults An array of playlists objects to be displayed on the page.
     * @returns A string of HTML suitable for being dropped on the page.
     */
    async getHTMLForSearchResults(searchResult) {
            if (searchResult === undefined){
                         return '<h4>No results found</h4>';
             }

     let html = '<table><tr><th>Title</th></tr>';
                                 {
                                    html += `
                                    <tr>
                                        <td>
                                            <a ${searchResult.title}</a>
                                        </td>
                                          </tr>`;
                                }
                                html += '</table>';
                                return html;
                }
                            }

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const getItems = new GetItems();
    getItems.mount();
};

window.addEventListener('DOMContentLoaded', main);
