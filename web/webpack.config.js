const path = require('path');
const CopyPlugin = require("copy-webpack-plugin");
const Dotenv = require('dotenv-webpack');

// Get the name of the appropriate environment variable (`.env`) file for this build/run of the app
const dotenvFile = process.env.API_LOCATION ? `.env.${process.env.API_LOCATION}` : '.env';

module.exports = {
  plugins: [
    new CopyPlugin({
      patterns: [
        {
          from: "static_assets", to: "../",
          globOptions: {
            ignore: ["**/.DS_Store"],
          },
        },
      ],
    }),
    new Dotenv({ path: dotenvFile }),
  ],
  optimization: {
    usedExports: true
  },
  entry: {
    GETMOVIE: path.resolve(__dirname, 'src', 'pages', 'GETMOVIE.js'),
    GETTVSHOW: path.resolve(__dirname, 'src', 'pages', 'GETTVSHOW.js'),
    SEARCHBOOKS: path.resolve(__dirname, 'src', 'pages', 'SEARCHBOOKS.js'),
    CREATEUSER: path.resolve(__dirname, 'src', 'pages', 'CREATEUSER.js'),
    UPDATEUSER: path.resolve(__dirname, 'src', 'pages', 'UPDATEUSER.js'),
    VIEWLISTS: path.resolve(__dirname, 'src', 'pages', 'VIEWLISTS.js'),
    WRITEREVIEW: path.resolve(__dirname, 'src', 'pages', 'WRITEREVIEW.js'),
    ADDBOOKTOLIST: path.resolve(__dirname, 'src', 'pages', 'ADDBOOKTOLIST.js'),
    DELETEUSER: path.resolve(__dirname, 'src', 'pages', 'DELETEUSER.js'),
  },
  output: {
    path: path.resolve(__dirname, 'build', 'assets'),
    filename: '[name].js',
    publicPath: '/assets/'
  },
  devServer: {
    static: {
      directory: path.join(__dirname, 'static_assets'),
    },
    port: 8000,
    client: {
      // overlay shows a full-screen overlay in the browser when there are js compiler errors or warnings
      overlay: true,
    },
  }
}