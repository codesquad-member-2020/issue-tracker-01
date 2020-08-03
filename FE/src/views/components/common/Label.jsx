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
        display: "inline-block",
        maxWidth: "150px",
        overflow: "hidden",
        textOverflow: "ellipsis",
        whiteSpace: "nowrap",
      }}
    >
      {title}
    </Tag>
  );
};

export default Label;
