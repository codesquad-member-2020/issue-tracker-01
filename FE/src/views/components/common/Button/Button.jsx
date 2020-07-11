import React from "react";
import { StyledButton } from "./Button.js";

const MyButton = ({ type, size, shape, text, bgcolor, block }) => {
  return (
    <StyledButton
      type={type}
      size={size}
      shape={shape}
      style={{ background: bgcolor, width: block ? "100%" : "" }}
    >
      {text}
    </StyledButton>
  );
};

export default MyButton;
