import React from "react";
import { Tag } from "antd";

const Label = ({ text, color }) => {
  return <Tag color={color}>{text}</Tag>;
};

export default Label;
