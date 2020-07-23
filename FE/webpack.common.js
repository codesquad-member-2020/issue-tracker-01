const path = require("path");
const webpack = require("webpack");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const { CleanWebpackPlugin } = require("clean-webpack-plugin");
const Dotenv = require("dotenv-webpack");

module.exports = {
  entry: "./src/index.jsx",
  module: {
    rules: [
      {
        test: /\.jsx?$/,
        loader: "babel-loader",
        options: {
          presets: ["@babel/preset-env"],
        },
        exclude: /node_modules/,
      },
      {
        test: /\.css$/,
        use: ["style-loader", "css-loader"],
      },
      {
        test: /\.(png|jp(e*)g)$/,
        loader: "url-loader",
        options: {
          limit: 8000,
          fallback: "responsive-loader",
          name: "images/[hash:8]-[name].[ext]",
        },
      },
      {
        test: /\.svg$/,
        use: [
          {
            loader: "svg-url-loader",
          },
        ],
      },
    ],
  },
  resolve: {
    extensions: [".js", ".jsx"],
    alias: {
      Styles: path.resolve(__dirname, "./src/styles/"),
      "@": path.resolve(__dirname, "./src/views/components/"),
      Pages: path.resolve(__dirname, "./src/views/pages/"),
      Assets: path.resolve(__dirname, "./src/assets/"),
      Utils: path.resolve(__dirname, "./src/utils/"),
      Hooks: path.resolve(__dirname, "./src/hooks/"),
      Store: path.resolve(__dirname, "./src/store/"),
    },
  },
  plugins: [
    new webpack.HotModuleReplacementPlugin(),
    new HtmlWebpackPlugin({
      filename: "index.html",
      template: "public/index.html",
      favicon: "public/favicon.svg",
    }),
    new CleanWebpackPlugin(),
    new Dotenv(),
  ],
};
