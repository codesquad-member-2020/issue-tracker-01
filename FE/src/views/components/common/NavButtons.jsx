import React from "react";
import { NavLink } from "react-router-dom";
import styled from "styled-components";

const NavButtons = ({ menus }) => {
  const activeStyle = {
    color: "#1890ff",
  };

  return (
    <NavWrapper>
      {menus.length > 0 &&
        menus.map(({ icon, title, param }) => (
          <LinkWrapper key={param}>
            <StyledLink to={param} activeStyle={activeStyle}>
              {icon}
              <Title>{title}</Title>
            </StyledLink>
          </LinkWrapper>
        ))}
    </NavWrapper>
  );
};

export default NavButtons;

const NavWrapper = styled.div`
  height: 100%;
  height: 32px;
  display: flex;
  border: 1px solid #d9d9d9;
  border-radius: 2px;
`;

const LinkWrapper = styled.div`
  height: 100%;
  :not(:last-child) {
    border-right: 1px solid #d9d9d9;
  }
`;

const StyledLink = styled(NavLink)`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 4px 15px;
  color: #000000a6;
`;

const Title = styled.span`
  margin-left: 5px;
`;
