import { createGlobalStyle } from "styled-components";
import reset from "styled-reset";

const GlobalStyle = createGlobalStyle`
  ${reset}
  * {
    box-sizing: border-box;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, 'Noto Sans', sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol', 'Noto Color Emoji' !important;
    font-size: 16px;
    &:before, &:after {
      box-sizing: border-box;
    }
  }

  a {
    cursor: pointer;
  }

  #root {
    height: 100%;
  }

  main {
    height: 100%;
  }

  .container {
    height: 100%;
    padding: 2% 15%;
  }
`;

export default GlobalStyle;
