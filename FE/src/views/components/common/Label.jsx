import React from "react";
import { Tag } from "antd";

const Label = ({ title, color }) => {
  return (
    <Tag
      color={color}
      style={{
        height: "22px",
        width: "fit-content",
        fontSize: "12px",
        fontWeight: "bold",
        lineHeight: "1.5",
        marginRight: "3px",
        borderRadius: "12px",
        display: "flex",
        alignItems: "center",
      }}
    >
      {title}
    </Tag>
  );
};

export default Label;
