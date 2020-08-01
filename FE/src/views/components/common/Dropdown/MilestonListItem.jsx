import React from "react";
import styled from "styled-components";
import { CheckOutlined } from "@ant-design/icons";

const MilestonListItem = ({ props }) => {
  const { id, title, selected } = props;
  return (
    <Item className="item" data-index={id}>
      <CheckOutlined style={{ marginRight: "10px", visibility: selected ? "visible" : "hidden" }} />
      {title}
    </Item>
  );
};

export default MilestonListItem;

const Item = styled.li`
  display: flex;
  align-items: center;
  font-weight: 600;
`;
