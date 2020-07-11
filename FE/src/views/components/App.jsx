import React from "react";
import Router from "./Router";
import GlobalStyle from "Styles/GlobalStyle";
import "antd/dist/antd.css";

const App = () => {
  return (
    <>
      <Router />
      <GlobalStyle />
    </>
  );
};

export default App;
