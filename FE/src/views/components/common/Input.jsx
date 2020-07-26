import React from "react";
import styled from "styled-components";

const Input = ({ placeholder, defaultValue, value, style, onChange, readOnly }) => {
  return (
    <StyledInput
      type="text"
      placeholder={placeholder}
      defaultValue={defaultValue}
      value={value}
      style={style}
      onChange={onChange}
      readOnly={readOnly}
    />
  );
};

export default Input;

const StyledInput = styled.input`
  width: 100%;
  height: 32px;
  border: 1px solid #e1e4e8;
  border-radius: 2px;
  padding: 4px 8px;
  line-height: 1.5715;
  transition: all 0.2s;

  ::placeholder {
    color: lightgray;
  }

  &:hover,
  &:focus,
  &:active {
    border-color: #40a9ff;
    outline: none;
  }
`;
