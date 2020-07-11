import React from "react";
import { Button } from "antd";

const MyButton = ({ type, size, shape, text, bgcolor, block }) => {
  return (
    <Button
      type={type}
      size={size}
      shape={shape}
      style={{ background: bgcolor, width: block ? "100%" : "" }}
    >
      {text}
    </Button>
  );
};

export default MyButton;
