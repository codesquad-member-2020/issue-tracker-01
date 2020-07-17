import React from "react";
import styled from "styled-components";
import githubLogo from "Assets/github.svg";

const Header = () => {
  return (
    <StyledHeader>
      <img src={githubLogo} style={{ width: "30px" }} />
    </StyledHeader>
  );
};

const StyledHeader = styled.header`
  background: #fafbfc;
  height: 50px;
  border-bottom: 1px solid #e1e4e8;
  padding: 0 10px;
  display: flex;
  align-items: center;
  width: 100%;
  position: fixed;
  top: 0;
  left: 0;
`;

export default Header;
