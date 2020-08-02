import React from "react";
import styled from "styled-components";
import User from "@/common/User";
import { CheckOutlined } from "@ant-design/icons";

const UserListItem = ({ props }) => {
  const { userId, nickname, profileImage, selected } = props;
  return (
    <Item className="item" data-index={userId}>
      <CheckOutlined style={{ marginRight: "10px", visibility: selected ? "visible" : "hidden" }} />
      <User nickname={nickname} profileImage={profileImage} size="small" />
    </Item>
  );
};

export default UserListItem;

const Item = styled.li`
  display: flex;
  align-items: center;
  font-weight: 600;
`;
