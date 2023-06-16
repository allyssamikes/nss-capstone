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
        'getBook', 'getTVShow', 'getMovie', 'getToReadList', 'getReadList', 'getCurrentlyReading', 'writeReview',
        'createUser', 'updateUser', 'deleteUser', 'addBookToCurrentlyReading', 'addBookToToReadList', 'addBookToReadList', 'searchBooks'];

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
    console.log("cc88");
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


    async getToReadList(userId, errorCallback) {
    console.log("get to read")
        try {
           const response = await this.axiosClient.get(`users/${userId}/toReadList`);
            return response.data.toReadList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

        async getReadList(userId, errorCallback) {
        console.log("get read")
            try {
               const response = await this.axiosClient.get(`users/${userId}/readList`);
                return response.data.readList;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }

            async getCurrentlyReading(userId, errorCallback) {
            console.log("get currently")
                try {
                   const response = await this.axiosClient.get(`users/${userId}/currentlyReading`);
                    return response.data.currentlyReading;
                } catch (error) {
                    this.handleError(error, errorCallback)
                }
            }

    async writeReview(userId, rating, review, UUIDOfEntity, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create reviews.");
            const response = await this.axiosClient.post(`reviews`, {
                userId: userId,
                rating: rating,
                review: review,
               UUIDOfEntity: UUIDOfEntity,
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
                console.log("cs 158");

                const response = await this.axiosClient.post(`users`, {
                    userId: userId,
                    name: name,
                }, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                console.log("cs168");
                return response.data.user;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }

       async updateUser(userId, name, errorCallback) {
            try {
                const token = await this.getTokenOrThrow("Only authenticated users can update users.");
            console.log("update 178")
                const response = await this.axiosClient.put(`users/${userId}`, {
                    userId: userId,
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
                        console.log("delete196")
                        const response = await this.axiosClient.delete(`users/${userId}`, {data : {
                        userId: userId
                        }, headers: {
                          Authorization: `Bearer ${token}`
                    }},
                    );
                        return response.data.user;
                    } catch (error) {
                        this.handleError(error, errorCallback)
                    }
                }

    async addBookToCurrentlyReading(userId, isbn, errorCallback) {
            try {
                const token = await this.getTokenOrThrow("Only authenticated users can add a book.");
                const response = await this.axiosClient.post(`users/${userId}/currentlyReading`, {
                    userId: userId,
                    isbn: isbn,
              }, {
                  headers: {
                      Authorization: `Bearer ${token}`
                  }
              });
                return response.data.currentlyReading;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }

            async addBookToToReadList(userId, isbn, errorCallback) {
                    try {
                        const token = await this.getTokenOrThrow("Only authenticated users can add a book.");
                        const response = await this.axiosClient.post(`users/${userId}/toReadList`, {
                            userId: userId,
                            isbn: isbn,
                      }, {
                          headers: {
                              Authorization: `Bearer ${token}`
                          }
                      });
                        return response.data.toReadList;
                    } catch (error) {
                        this.handleError(error, errorCallback)
                    }
                }

                    async addBookToReadList(userId, isbn, errorCallback) {
                            try {
                                const token = await this.getTokenOrThrow("Only authenticated users can add a book.");
                                const response = await this.axiosClient.post(`users/${userId}/readList`, {
                                    userId: userId,
                                    isbn: isbn,
                              }, {
                                  headers: {
                                      Authorization: `Bearer ${token}`
                                  }
                              });
                                return response.data.readList;
                            } catch (error) {
                                this.handleError(error, errorCallback)
                            }
                        }


    async searchBooks(criteria, errorCallback) {
        try{
         const queryParams = new URLSearchParams({ q: criteria })
         const queryString = queryParams.toString();

          const response = await this.axiosClient.get(`books/search?${queryString}`);
          return response.data.books;
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