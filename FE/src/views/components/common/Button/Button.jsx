import React from "react";
import { StyledButton } from "./Button.js";

const MyButton = ({ type, size, shape, text, bgcolor }) => {
  return (
    <StyledButton type={type} size={size} shape={shape} style={{ background: bgcolor }}>
      {text}
    </StyledButton>
  );
};

export default MyButton;
