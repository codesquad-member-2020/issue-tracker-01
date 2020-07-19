import React from "react";
import { Button } from "antd";

const MyButton = ({ type, size, shape, icon, text, bgcolor, block, onClick }) => {
  return (
    <Button
      type={type}
      size={size}
      shape={shape}
      style={{
        background: bgcolor,
        width: block ? "100%" : "",
        display: "flex",
        alignItems: "center",
      }}
      onClick={onClick}
    >
      {icon && <div style={{ marginRight: "5px" }}>{icon}</div>}
      <div>{text}</div>
    </Button>
  );
};

export default MyButton;
