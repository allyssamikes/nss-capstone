import CapstoneClient from '../api/CAPSTONECLIENT';
 import Header from '../components/header';
 import BindingClass from '../util/bindingClass';
 import DataStore from '../util/DataStore';

 /**
  * Logic needed display the page of the website which displays the details of an itinerary.
  */
 class ViewLists extends BindingClass {
     constructor() {
         super();
         this.bindClassMethods(['mount', 'submit'], this);
         this.dataStore = new DataStore();

         this.dataStoreSearch = new DataStore;
         this.header = new Header(this.dataStore);
     }
     /**
      * Add the header to the page and load the Client.
      */
     mount() {
         document.getElementById('lists').addEventListener('click', this.submit);
         this.header.addHeaderToPage();

         this.client = new CapstoneClient();
     }
         async submit(evt) {
             evt.preventDefault();

             const errorMessageDisplay = document.getElementById('error-message');
             errorMessageDisplay.innerText = ``;
             errorMessageDisplay.classList.add('hidden');

             const getButton = document.getElementById('lists');
             const origButtonText = getButton.innerText;
             getButton.innerText = 'Loading...';

             //user input
             const userId = document.getElementById('userId').value;
             if(userId == null || userId === '') {
             getButton.innerText = origButtonText;
             return;}

             const toReadList = await this.client.getToReadList(userId, (error) => {
                 getButton.innerText = origButtonText;
                 const errorMessageDisplay = document.getElementById('error-message');
                 errorMessageDisplay.innerText = `Error: ${error.message}`;
                 errorMessageDisplay.classList.remove('hidden');
             });
             this.dataStore.set('toReadList', toReadList);

              const readList = await this.client.getReadList(userId, (error) => {
                              getButton.innerText = origButtonText;
                              const errorMessageDisplay = document.getElementById('error-message');
                              errorMessageDisplay.innerText = `Error: ${error.message}`;
                              errorMessageDisplay.classList.remove('hidden');
              });
               this.dataStore.set('readList', readList);

             const currentlyReading = await this.client.getCurrentlyReading(userId, (error) => {
                                           getButton.innerText = origButtonText;
                                           const errorMessageDisplay = document.getElementById('error-message');
                                           errorMessageDisplay.innerText = `Error: ${error.message}`;
                                           errorMessageDisplay.classList.remove('hidden');
             });
             this.dataStore.set('currentlyReading', currentlyReading);
                console.log(currentlyReading +  "currently");
                console.log(toReadList + "to");
                console.log(readList + "read")
            const submit1ResultsContainer = document.getElementById('results1-container');
            submit1ResultsContainer.classList.remove('hidden');

          const submit2ResultsContainer = document.getElementById('results2-container');
           submit2ResultsContainer.classList.remove('hidden');

           const submit3ResultsContainer = document.getElementById('results3-container');
           submit3ResultsContainer.classList.remove('hidden');

        if (currentlyReading === undefined) {
                 return '<h4>Currently Reading List Is Empty</h4>';
            }
        if (toReadList === undefined) {
                 return '<h4>To Read List Is Empty</h4>';
            }
            if (readList === undefined) {
                 return '<h4>Read List Is Empty</h4>';
            }

            let book;
            let book1Html = '';
                 for (book of toReadList) {

                      bookHtml += `
                            <li class="toReadList">
                                         <span class="Title">${book.title}</span>
                                         <span class="space">${" : "}</span>
                                         <span class="Author">${book.author}</span>
                                         <span class="space">${"   :   "}</span>
                                         <span class="Genre">${activity.GENRE}</span>
                                         <span class="space">${"   :   "}</span>
                                         <span class="Year Published">${book.yearPublished}</span>
                                          <span class="space">${"   :   "}</span>
                                         <span class="Length">${book.lengthInPages}</span>
                                         <span class="space">${"   :   "}</span>
                            </li>
                             <br>
                       `;
                 }
                      let book2Html = '';
                                  for (book of currentlyReading) {

                                       bookHtml += `
                                             <li class="currentlyReading">
                                                          <span class="Title">${book.title}</span>
                                                          <span class="space">${" : "}</span>
                                                          <span class="Author">${book.author}</span>
                                                          <span class="space">${"   :   "}</span>
                                                          <span class="Genre">${activity.GENRE}</span>
                                                          <span class="space">${"   :   "}</span>
                                                          <span class="Year Published">${book.yearPublished}</span>
                                                           <span class="space">${"   :   "}</span>
                                                          <span class="Length">${book.lengthInPages}</span>
                                                          <span class="space">${"   :   "}</span>
                                             </li>
                                              <br>
                                        `;
                                  }
                 document.getElementById('currentlyReading').innerHTML = book2Html;

                      let book3Html = '';
                                                   for (book of readList) {
                                                        bookHtml += `
                                                              <li class="readList">
                                                                           <span class="Title">${book.title}</span>
                                                                           <span class="space">${" : "}</span>
                                                                           <span class="Author">${book.author}</span>
                                                                           <span class="space">${"   :   "}</span>
                                                                           <span class="Genre">${activity.GENRE}</span>
                                                                           <span class="space">${"   :   "}</span>
                                                                           <span class="Year Published">${book.yearPublished}</span>
                                                                            <span class="space">${"   :   "}</span>
                                                                           <span class="Length">${book.lengthInPages}</span>
                                                                           <span class="space">${"   :   "}</span>
                                                              </li>
                                                               <br>
                                                         `;
                                                   }
                                  document.getElementById('readList').innerHTML = book3Html;

                  document.getElementById('view-lists-form').reset;
                  }

 }
 /**
  * Main method to run when the page contents have loaded.
  */
 const main = async () => {
     const viewLists = new ViewLists();
     viewLists.mount();
 };

 window.addEventListener('DOMContentLoaded', main);