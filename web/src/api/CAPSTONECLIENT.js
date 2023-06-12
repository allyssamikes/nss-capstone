import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the MusicPlaylistService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class CapstoneClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'getTokenOrThrow',
        'getBook', 'getTVShow', 'getMovie', 'getItineraryActivities', 'createReview',
        'search', 'createUser, 'addActivityToItinerary', 'removeActivityFromItinerary','searchBooks'];

        this.bindClassMethods(methodsToBind, this);
        this.authenticator = new Authenticator();
        this.props = props;
        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }


    async getBook(isbn, errorCallback) {

          try {
              const response = await this.axiosClient.get(`books/${isbn}`);
              return response.data.book;
          } catch (error) {
              this.handleError(error, errorCallback)
          }
      }


    async getTVShow(title, director, errorCallback) {

          try {
              const response = await this.axiosClient.get(`tvshows/${title}/${director}`);
              return response.data.tvshow;
          } catch (error) {
              this.handleError(error, errorCallback)
          }
      }


    async getMovie(title, director, errorCallback) {

          try {
              const response = await this.axiosClient.get(`movies/${title}/${director}`);
              return response.data.movie;
          } catch (error) {
              this.handleError(error, errorCallback)
          }
      }

    /**
     * Get the songs on a given playlist by the playlist's identifier.
     * @param id Unique identifier for a playlist
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The list of activities in an itinerary.
     */
    async getItineraryActivities(email, tripName, errorCallback) {

        try {
           // const response = await this.axiosClient.get(`itineraries/${id}/activities`);
           const response = await this.axiosClient.get(`itineraries/${email}/${tripName}/activities`);
            return response.data.activities;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

        /**
         * Create a new itinerary owned by the current user.
         * @param tripName String The name of the itinerary to create.
         * @param tags Metadata tags to associate with a itinerary.
         * @param users Metadata users to associate with a itinerary.
         * @param users Metadata cities to associate with a itinerary.
         * @param errorCallback (Optional) A function to execute if the call fails.
         * @returns The itinerary that has been created.
         */
    async writeReview(userId, rating, review, UUIDOfEntity, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create reviews.");
            const response = await this.axiosClient.post(`reviews`, {
                userId: userId;
                rating: rating;
                review: review;
               UUIDOfEntity: UUIDOfEntity;,
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.review;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }


        async createUser(userId, name, errorCallback) {
            try {
                const token = await this.getTokenOrThrow("Only authenticated users can create users.");

                const response = await this.axiosClient.post(`users`, {
                    userId: userId;,
                    name: name,
                }, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                return response.data.user;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }

       async updateUser(userId, name, errorCallback) {
            try {
                const token = await this.getTokenOrThrow("Only authenticated users can update users.");

                const response = await this.axiosClient.put(`users/${userId}`, {
                    userId: userId;,
                    name: name,
                }, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                return response.data.user;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }

               async deleteUser(userId, errorCallback) {
                    try {
                        const token = await this.getTokenOrThrow("Only authenticated users can delete users.");

                        const response = await this.axiosClient.delete(`users/${userId}`, {
                            userId: userId;,
                        }, {
                            headers: {
                                Authorization: `Bearer ${token}`
                            }
                        });
                        return response.data.user;
                    } catch (error) {
                        this.handleError(error, errorCallback)
                    }
                }

        /**
         * Adds requested activity to requested itinerary's list of activities.
         * @param email A string containing partition key for itinerary to pass to the API.
         * @param tripName A string containing sort key for itinerary to pass to the API.
         * @param cityCountry A string containing partition key for activity to pass to the API.
         * @param name A string containing sort key for activity to pass to the API.
         * @param errorCallback (Optional) A function to execute if the call fails.
         * @returns The list of activities that have been updated in the itinerary.
         */
    async addActivityToItinerary(email, tripName, cityCountry, name, errorCallback) {
            try {
                const token = await this.getTokenOrThrow("Only authenticated users can add a song to a playlist.");
                const response = await this.axiosClient.post(`itineraries/${email}/${tripName}/activities`, {
                    email: email,
                    tripName: tripName,
                    cityCountry: cityCountry,
                    name: name
              }, {
                  headers: {
                      Authorization: `Bearer ${token}`
                  }
              });
                return response.data.activityList;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }
    async removeActivityFromItinerary(email, tripName, cityCountry, name, errorCallback) {
            try {
                const token = await this.getTokenOrThrow("Only authenticated users can add a song to a playlist.");
                const response = await this.axiosClient.put(`itineraries/${email}/${tripName}/activities`, {
                    email: email,
                    tripName: tripName,
                    cityCountry: cityCountry,
                    name: name
              }, {
                  headers: {
                      Authorization: `Bearer ${token}`
                  }
              });
                return response.data.activityList;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }


    }
    async searchBooks(genre, errorCallback) {
        try{
            const response = await this.axiosClient.get(`books/search?genre=${genre}`);
            return response.data.bookModelsList;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
}