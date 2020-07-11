import styled, { css } from "styled-components";
import { Button } from "antd";

export const StyledButton = styled(Button)`
  &.ant-btn-round {
    color: #fff;
    transition: none;
    border: none;
    cursor: default;
    &:hover,
    &:active {
      color: #fff;
      border: none;
    }
  }
`;
