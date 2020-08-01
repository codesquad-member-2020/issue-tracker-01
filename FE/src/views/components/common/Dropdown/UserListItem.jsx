import React from "react";
import styled from "styled-components";
import User from "@/common/User";
import { CheckOutlined } from "@ant-design/icons";

const UserListItem = ({ props }) => {
  const { id, nickname, profileImage, selected } = props;
  return (
    <Item className="item" data-index={id}>
      <CheckOutlined style={{ marginRight: "10px", visibility: selected ? "visible" : "hidden" }} />
      <User nickname={nickname} profileImage={profileImage} size="small" />
    </Item>
  );
};

export default UserListItem;

const Item = styled.li`
  cursor: pointer;
  padding: 5px 12px;
  line-height: 22px;
  transition: all 0.3s;
  display: flex;
  align-items: center;
  :hover {
    background: #f5f5f5;
  }
  :not(:first-child) {
    border-top: 1px solid lightgray;
  }
`;
