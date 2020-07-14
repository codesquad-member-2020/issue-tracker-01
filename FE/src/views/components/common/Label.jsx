import React from "react";
import { Tag } from "antd";

const Label = ({ title, color }) => {
  return (
    <Tag
      color={color}
      style={{
        fontSize: "12px",
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
