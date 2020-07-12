module.exports = {
  presets: ["@babel/preset-react", "@babel/preset-env"],
  plugins: [
    [
      "@babel/plugin-transform-runtime",
      {
        corejs: 3,
        regenerator: true,
      },
    ],
    [
      "import",
      {
        libraryName: "antd",
        style: "css",
      },
    ],
  ],
};
